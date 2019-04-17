package ru.spaceouter.infoscan.auth;

/**
 * @author danil
 * @date 14.04.19
 */
public interface SimpleAuthService {

   AuthResultDTO authenticateUser(String log, String pass);

}
