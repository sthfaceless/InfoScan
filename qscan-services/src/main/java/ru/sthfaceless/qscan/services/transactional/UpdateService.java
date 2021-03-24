package ru.sthfaceless.qscan.services.transactional;

/**
 * @author danil
 * @date 20.04.19
 */
public interface UpdateService {

    void updateEmail(long userId, String email);

    void confirmEmailUpdating(String uuid);

    void updatePassword(long userId, String pass, String newPass);

}
