package com.mgf.email;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Component;

/**
 * Created by Makda on 2016-02-10.
 */
@Component
public class TemplateMessage extends SimpleMailMessage {
    public TemplateMessage(){
        setFrom("gargas.magdalena@gmail.com");
        setSubject(":*");
    }
}
