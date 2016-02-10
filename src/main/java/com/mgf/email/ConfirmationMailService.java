package com.mgf.email;

/**
 * Created by Makda on 2016-02-09.
 */

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.mail.MailException;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Component;

@Component
@Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class ConfirmationMailService {

    @Autowired
    private MailSender mailSender;

    @Autowired
    private SimpleMailMessage templateMessage;

    public void sendEmail(String email, String hash) {

        templateMessage.setTo(email);
        templateMessage.setText("http://192.168.1.12:8080/confirm/" + hash);
        try{
            this.mailSender.send(templateMessage);
        }
        catch (MailException ex) {
            System.err.println(ex.getMessage());
        }
    }

}