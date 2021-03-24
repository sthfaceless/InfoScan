package ru.spaceouter.infoscan.services.transactional;

/**
 * @author danil
 * @date 21.04.19
 */
public interface EmailService {

    boolean sendActivateAccountMessage(String email, String activateString);

    boolean sendConfirmEmailMessage(String email, String activateString);

    boolean sendConfirmPasswordRestoreMessage(String email, String activateString);

}
