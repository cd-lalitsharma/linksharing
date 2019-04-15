package com.ttn.linksharing.service;

import com.ttn.linksharing.co.SignupCo;
import com.ttn.linksharing.entity.User;
import com.ttn.linksharing.enums.RolesEnum;
import com.ttn.linksharing.repository.UserRepository;
import com.ttn.linksharing.service.impl.SignupServiceInterface;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.slf4j.Logger;

@Service
public class SignupService implements SignupServiceInterface {
    
    @Autowired
    UserRepository userRepository;
    
    @Autowired
    MailSenderService mailSenderService;
   
   private  static final Logger logger= LoggerFactory.getLogger(SignupService.class);
    
    public User saveUser(SignupCo signupCo,
                         MultipartFile multipartFile,
                         String uploadPath){
    
        User user = new User();
        user.setFirstName(signupCo.getFirstName());
        user.setLastName(signupCo.getLastName());
        user.setEmail(signupCo.getEmail());
        user.setUsername(signupCo.getUsername());
        user.setPassword(signupCo.getPassword());
        user.setRole(RolesEnum.USER.toString());
//        user.setCreateDate(new Date());
        user.setIsActive(1);
        
        //upload user pic and send email to confirm account
        
        if (multipartFile.isEmpty()){
            user.setPhoto("img/user.png");
    
        }else{
            logger.info("trying to upload file");
            try {
                byte[] bytes= multipartFile.getBytes();
                Path path= Paths.get(uploadPath+multipartFile.getOriginalFilename());
                Files.write(path,bytes);
                
                String photoPath="uploads/profilePhotos/"+multipartFile.getOriginalFilename();
                user.setPhoto(photoPath);
                System.out.println("");
        
            } catch (IOException e) {
                logger.error("error while uploading file",e);
                e.printStackTrace();
        
            }
        }
        
        
        logger.info("upload success now saving user to db");
        User savedUser=userRepository.save(user);
        if (savedUser!=null && savedUser.getFirstName().equals(signupCo.getFirstName())){
            
            
            logger.info("returning saved user to signup controller and sending mail");
            String name=savedUser.getFirstName()+" "+savedUser.getLastName();
            String email=savedUser.getEmail();
            String subject="signup success on linksharing";
            String message="you are successfully signed up on linksharing. kindly login to start using linksharing.";
            mailSenderService.prepareMail(name,email,subject,message);
            return savedUser;
        }else{
            return null;
        }
     
    }
}
