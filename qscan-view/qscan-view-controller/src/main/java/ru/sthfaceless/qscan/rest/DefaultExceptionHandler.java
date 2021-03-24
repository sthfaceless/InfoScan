package ru.sthfaceless.qscan.rest;

import org.hibernate.HibernateException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import ru.spaceouter.infoscan.exceptions.*;

import javax.mail.MessagingException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;
import java.util.List;

/**
 * @author danil
 * @date 20.04.19
 */
@RestControllerAdvice
public class DefaultExceptionHandler extends AbstractRestController{

    @ExceptionHandler({UnexpectedServerException.class, MessagingException.class, HibernateException.class})
    public ResponseEntity<?> unexpectedServerException(Exception exception){

        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(InvalidAuthenticationException.class)
    public ResponseEntity<?> invalidAuthenticationException(
            final HttpServletResponse response){

        List<Cookie> cookies = Arrays.asList(new Cookie("auth_token", ""),
                new Cookie("expired_token", ""));
        for(Cookie cookie : cookies) {
            cookie.setMaxAge(0);
            response.addCookie(cookie);
        }

        return new ResponseEntity<>(HttpStatus.NON_AUTHORITATIVE_INFORMATION);
    }

    @ExceptionHandler({NotFoundException.class, NotExistException.class})
    public ResponseEntity<?> notFoundException(){

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(UnauthorizedException.class)
    public ResponseEntity<?> unauthorizedException(){

        return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(WrongArgumentsException.class)
    public ResponseEntity<?> wrongArgumentException(){

        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<?> accessDeniedException(){

        return new ResponseEntity<>(HttpStatus.FORBIDDEN);
    }

}
