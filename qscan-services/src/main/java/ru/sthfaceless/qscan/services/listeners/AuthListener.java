package ru.sthfaceless.qscan.services.listeners;

import javax.servlet.http.HttpServletResponse;
import java.util.Date;

/**
 * @author danil
 * @date 23.04.19
 */
public interface AuthListener {

    void authSuccess(String token, Date expiredDate, HttpServletResponse response);

    void logoutSuccess(HttpServletResponse response);

}
