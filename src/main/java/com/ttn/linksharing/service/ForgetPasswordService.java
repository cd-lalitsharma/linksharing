package com.ttn.linksharing.service;


import com.ttn.linksharing.entity.User;
import com.ttn.linksharing.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ForgetPasswordService {

    @Autowired
    UserRepository userRepository;
    
    @Autowired
    MailSenderService mailSenderService;
    
    public boolean checkForEmailExistance(String email){
        
        return userRepository.existsUserByEmail(email);
    }
    
    public User getUserByEmail(String email){
        
        User user=userRepository.getUserByEmail(email);
        
        return user;
    }
    
    public void resetPassword(String email) {
        User user=getUserByEmail(email);
        String password = user.getPassword();
        String name=user.getFirstName();
        
        String subject="Forget Password Request for linksharing";
        String message="you password is "+password+" .please use it for login.";
        
        mailSenderService.prepareMail(name,email,subject,message);
    }
}
