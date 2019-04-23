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
import ru.spaceouter.infoscan.dto.auth.UserAuthDTO;
import ru.spaceouter.infoscan.dto.model.AuthModelDTO;
import ru.spaceouter.infoscan.dto.view.AuthCredentialsDTO;
import ru.spaceouter.infoscan.dto.view.AuthTokenDTO;
import ru.spaceouter.infoscan.exceptions.InvalidAuthenticationException;
import ru.spaceouter.infoscan.exceptions.UnauthorizedException;
import ru.spaceouter.infoscan.model.AuthSpringDAO;
import ru.spaceouter.infoscan.model.entities.user.AuthEntity;
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
    public UserAuthDTO getAuthUser(String token) throws UnauthorizedException{

        if(StringUtils.isEmpty(token))
            throw new UnauthorizedException();

        return getUserFromDatabase(token);
    }

    @CachePut(cacheNames = "authentications", key = "#token")
    @Transactional(readOnly = true)
    public UserAuthDTO getUserFromDatabase(String token) throws UnauthorizedException{

        AuthEntity authEntity = authSpringDAO.findByToken(token);

        if(authEntity == null)
            throw new UnauthorizedException();

        return new UserAuthDTO(authEntity.getAuthId(),
                authEntity.getUsername());
    }

    @Override
    public UserAuthDTO authWithCredentials(AuthCredentialsDTO authCredentialsDTO,
                                           HttpServletResponse response) throws InvalidAuthenticationException {

        AuthModelDTO authModelDTO = authSpringDAO.findByUsername(authCredentialsDTO.getLogin());
        if(authModelDTO == null)
            throw new InvalidAuthenticationException();

        if(!bCryptPasswordEncoder.matches(authCredentialsDTO.getPassword(), authModelDTO.getPassword()))
            throw new InvalidAuthenticationException();

        simpleAuth(authModelDTO.getUserId(), response);

        return new UserAuthDTO(authModelDTO.getUserId(), authModelDTO.getLogin());
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

        simpleAuth(authModel.getUserId(), response);

        return new UserAuthDTO(authModel.getUserId(), authModel.getLogin());
    }

    private void simpleAuth(long userId, HttpServletResponse response){

        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DATE, 30);
        Date expiredDate = new Date(calendar.getTimeInMillis());

        String securedToken = tokenService.nextToken();

        authSpringDAO.updateByIdTokenAndExpiredDate(userId, securedToken, expiredDate);

        authListener.authSuccess(securedToken, expiredDate,response);

    }

    @Override
    public void logout(String token,
                       HttpServletResponse response) throws UnauthorizedException {

        if(StringUtils.isEmpty(token))
            throw new UnauthorizedException();

        authSpringDAO.deleteToken(token);
        authListener.logoutSuccess(response);
    }
}
