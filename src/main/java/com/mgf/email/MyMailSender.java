package com.mgf.email;

import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Component;

import java.util.Properties;

/**
 * Created by Makda on 2016-02-10.
 */
@Component
public class MyMailSender extends JavaMailSenderImpl {
    public MyMailSender(){
        setHost("smtp.gmail.com");
        setPort(587);
        setUsername("gargas.magdalena@gmail.com");
        //TODO change password to work
        setPassword("----");
        setJavaMailProperties(new Properties(){{
            setProperty("mail.smtp.starttls.enable", "true");
        }});
    }
}
