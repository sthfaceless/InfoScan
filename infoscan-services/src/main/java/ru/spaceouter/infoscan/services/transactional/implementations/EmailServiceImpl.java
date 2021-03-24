package ru.spaceouter.infoscan.services.transactional.implementations;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import ru.spaceouter.infoscan.services.transactional.EmailService;

import javax.mail.internet.MimeMessage;

/**
 * @author danil
 * @date 21.04.19
 */
@Service
public class EmailServiceImpl implements EmailService {

    private final JavaMailSender javaMailSender;
    private final String domain;

    public EmailServiceImpl(JavaMailSender javaMailSender,
            @Value("$[server.domain") String domain){
        this.javaMailSender = javaMailSender;
        this.domain = domain;
    }

    @Override
    public boolean sendActivateAccountMessage(String email, String activateString){

        try {
            MimeMessage message = javaMailSender.createMimeMessage();
            MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(message);


            mimeMessageHelper.setTo(email);
            mimeMessageHelper.setSubject("InfoScan - Activation account info");
            mimeMessageHelper.setText("<html><body>" +
                    "<h3>To activate account follow next link:</h3>" +
                    "<a target=\""+domain+"/api/user/reg/"+activateString+"\">Click</a>" +
                    "</body></html>", true);


            javaMailSender.send(message);
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }

        return true;
    }

    @Override
    public boolean sendConfirmEmailMessage(String email, String activateString){

        try {
            MimeMessage message = javaMailSender.createMimeMessage();
            MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(message);


            mimeMessageHelper.setTo(email);
            mimeMessageHelper.setSubject("InfoScan - Confirm email info");
            mimeMessageHelper.setText("<html><body>" +
                    "<h3>To confirm new email follow link below::</h3>" +
                    "<a target=\""+domain+"/api/update/email/"+activateString+"\">Click</a>" +
                    "</body></html>", true);


            javaMailSender.send(message);
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }

        return true;
    }

    @Override
    public boolean sendConfirmPasswordRestoreMessage(String email, String activateString) {

        try {
            MimeMessage message = javaMailSender.createMimeMessage();
            MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(message);


            mimeMessageHelper.setTo(email);
            mimeMessageHelper.setSubject("InfoScan - Restore password info");
            mimeMessageHelper.setText("<html><body>" +
                    "<h3>To restore your password follow link below:</h3>" +
                    "<a target=\""+domain+"/api/user/restore/"+activateString+"\">Click</a>" +
                    "</body></html>", true);


            javaMailSender.send(message);
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }

        return true;
    }
}
