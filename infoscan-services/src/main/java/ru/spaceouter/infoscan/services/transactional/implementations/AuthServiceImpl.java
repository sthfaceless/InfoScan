package ru.spaceouter.infoscan.services.transactional.implementations;

import lombok.AllArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import ru.spaceouter.infoscan.dto.auth.AuthCredentialsDTO;
import ru.spaceouter.infoscan.dto.auth.AuthTokenDTO;
import ru.spaceouter.infoscan.dto.auth.UserAuthDTO;
import ru.spaceouter.infoscan.dto.model.AuthModelDTO;
import ru.spaceouter.infoscan.exceptions.InvalidAuthenticationException;
import ru.spaceouter.infoscan.exceptions.UnauthorizedException;
import ru.spaceouter.infoscan.model.AuthSpringDAO;
import ru.spaceouter.infoscan.services.TokenService;
import ru.spaceouter.infoscan.services.listeners.AuthListener;
import ru.spaceouter.infoscan.services.transactional.AuthService;

import javax.servlet.http.HttpServletResponse;
import java.util.Calendar;
import java.util.Date;

/**
 * @author danil
 * @date 21.04.19
 */
@Service
@AllArgsConstructor
@Transactional(propagation = Propagation.REQUIRED)
public class AuthServiceImpl implements AuthService<UserAuthDTO> {

    private final AuthSpringDAO authSpringDAO;

    private final AuthListener authListener;

    private final TokenService tokenService;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    /*
    * Cached method:
    * If user contains in cache then it will get from cache
    * else it will open transaction ang get from database
    * */
    @Override
    @Cacheable(cacheNames = "authentications", key = "#token")
    @Transactional(propagation = Propagation.NOT_SUPPORTED)
    public UserAuthDTO getAuthUser(String token) throws UnauthorizedException, InvalidAuthenticationException {

        return getUserFromDatabase(token);
    }

    @CachePut(cacheNames = "authentications", key = "#token")
    @Transactional(readOnly = true)
    public UserAuthDTO getUserFromDatabase(String token) throws UnauthorizedException, InvalidAuthenticationException {

        UserAuthDTO userAuthDTO = authSpringDAO.getAuthUserByToken(token);

        if(userAuthDTO == null) {
            throw new InvalidAuthenticationException();
        }

        return userAuthDTO;
    }

    @Override
    public UserAuthDTO authWithCredentials(AuthCredentialsDTO authCredentialsDTO,
                                           HttpServletResponse response) throws InvalidAuthenticationException {

        AuthModelDTO authModelDTO = authSpringDAO.findByUsername(authCredentialsDTO.getUsername());
        if(authModelDTO == null)
            throw new InvalidAuthenticationException();

        if(!bCryptPasswordEncoder.matches(authCredentialsDTO.getPassword(), authModelDTO.getPassword()))
            throw new InvalidAuthenticationException();

        String generatedToken = simpleAuth(authModelDTO.getAuthId(), response);

        return new UserAuthDTO(authModelDTO.getUserId(), authModelDTO.getUsername(), generatedToken);
    }

    @Override
    @CacheEvict(cacheNames = "authentications", key = "#authTokenDTO.token")
    public UserAuthDTO authWithToken(AuthTokenDTO authTokenDTO,
                                     HttpServletResponse response) throws InvalidAuthenticationException {

        AuthModelDTO authModel = authSpringDAO.findByTokenAndExpiredDateAfter(
                authTokenDTO.getToken(),
                new Date());

        if(authModel == null)
            throw new InvalidAuthenticationException();

        String generatedToken = simpleAuth(authModel.getAuthId(), response);

        return new UserAuthDTO(authModel.getUserId(), authModel.getUsername(), generatedToken);
    }

    private String simpleAuth(long authId, HttpServletResponse response){

        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DATE, 30);
        Date expiredDate = new Date(calendar.getTimeInMillis());

        String securedToken = tokenService.nextToken();
        authSpringDAO.updateByIdTokenAndExpiredDate(authId, securedToken, expiredDate);

        authListener.authSuccess(securedToken, expiredDate,response);

        return securedToken;
    }

    @Override
    @CacheEvict(cacheNames = "authentications", key = "#token")
    public void logout(String token,
                       HttpServletResponse response) throws UnauthorizedException {

        if(StringUtils.isEmpty(token))
            throw new UnauthorizedException();

        authSpringDAO.deleteToken(token);
        authListener.logoutSuccess(response);
    }
}
