package ru.spaceouter.infoscan.services.transactional;

/**
 * @author danil
 * @date 20.04.19
 */
public interface UpdateService {

    void updateEmail(long userId, String email);

    boolean confirmEmailUpdating(String uuid);

    boolean updatePassword(long userId, String pass, String newPass);

}
