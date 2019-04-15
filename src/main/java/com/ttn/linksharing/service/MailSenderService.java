package com.ttn.linksharing.service;


import com.ttn.linksharing.service.impl.MailSenderServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.internet.MimeMessage;

@Service
public class MailSenderService implements MailSenderServiceInterface {

    @Autowired
    private JavaMailSender mailSender;
    
    
    public void prepareMail(String name,String email,
                           String subject,String emailMessage){
    
        try {
            String customMessage=name +" , "+emailMessage;
            
            sendEmail(email,subject,customMessage);
        } catch (Exception e) {
            e.printStackTrace();
        }
    
    }
    
    
    private void sendEmail(String email,
                           String subject,String emailMessage) throws Exception{
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message);
        
        helper.setTo(email);
        helper.setFrom("mail@linksharing.com");
        helper.setText(emailMessage);
        helper.setSubject(subject);
    
        mailSender.send(message);
    }
    
}
