package ru.spaceouter.infoscan.services.listeners.implementations;

import org.springframework.stereotype.Component;
import ru.spaceouter.infoscan.services.listeners.AuthListener;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * @author danil
 * @date 23.04.19
 */
@Component
public class AuthListenerImpl implements AuthListener {

    @Override
    public void authSuccess(String token, Date expiredDate, HttpServletResponse response) {

        List<Cookie> cookies = Arrays.asList(new Cookie("auth_token", token),
                new Cookie("expired_token",
                        DateTimeFormatter.BASIC_ISO_DATE.format(expiredDate.toInstant())));

        int age = (int) ((expiredDate.getTime() - new Date().getTime())/1000);
        for (Cookie cookie : cookies) {
            cookie.setHttpOnly(true);
            cookie.setMaxAge(age);
            response.addCookie(cookie);
        }

    }

    @Override
    public void logoutSuccess(HttpServletResponse response) {

        List<Cookie> cookies = Arrays.asList(new Cookie("auth_token", ""),
                new Cookie("expired_token", ""));

        for (Cookie cookie : cookies){
            cookie.setMaxAge(0);
            response.addCookie(cookie);
        }

    }

}
