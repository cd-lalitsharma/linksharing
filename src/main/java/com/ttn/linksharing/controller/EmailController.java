package com.ttn.linksharing.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Controller;

@Controller
public class EmailController {
    
    @Autowired
    JavaMailSender mailSender;
    
    
}
