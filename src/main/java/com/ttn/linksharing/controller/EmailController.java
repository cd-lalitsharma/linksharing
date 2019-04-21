package com.ttn.linksharing.controller;


import com.ttn.linksharing.entity.User;
import com.ttn.linksharing.service.MailSenderService;
import com.ttn.linksharing.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

@Controller
public class EmailController {
    
    
    @Autowired
    UserService userService;
    
    @Autowired
    MailSenderService mailSenderService;
    
    @PostMapping("/sendTopicInvitation")
    @ResponseBody
    public String sendInvitation(@RequestParam("email") String email,
                                 @RequestParam("topicId") Integer topicId,
                                 HttpSession session){
    
        if (session.getAttribute("login") == "true") {
            
            Integer userId= (Integer) session.getAttribute("userId");
            User user= userService.getUserById(userId);
            
            String subject="Invitation to join Topic on Linksharing";
            String receiverEmail=email;
            String message="hi ,"+" "+user.getFirstName()+" invited you to join topic. "+"http://www.linksharing.com/topic/id/"+topicId;
            mailSenderService.prepareMail("",receiverEmail,subject,message);
            return "success";
        }else{
            return "error";
        }
        
    }
}
