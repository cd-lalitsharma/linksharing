package com.ttn.linksharing.service;

import com.ttn.linksharing.co.SignupCo;
import com.ttn.linksharing.entity.User;
import com.ttn.linksharing.repository.UserRepository;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Date;

import org.slf4j.Logger;

@Service
public class SignupService {
    
    @Autowired
   UserRepository userRepository;
   
   private  static final Logger logger= LoggerFactory.getLogger(SignupService.class);
    
    public  User saveUser(SignupCo signupCo,
                          MultipartFile multipartFile,
                          String uploadPath){
    
        User user = new User();
        user.setFirstName(signupCo.getFirstName());
        user.setLastName(signupCo.getLastName());
        user.setEmail(signupCo.getEmail());
        user.setUsername(signupCo.getUsername());
        user.setPassword(signupCo.getPassword());
        user.setCreateDate(new Date());
        user.setIsActive(1);
        
        //upload user pic and send email to confirm account
        logger.info("trying to upload file");
        try {
            byte[] bytes= multipartFile.getBytes();
            Path path= Paths.get(uploadPath+multipartFile.getOriginalFilename());
            Files.write(path,bytes);
            user.setPhoto(path.toString());
            System.out.println(path.toString());
            
        } catch (IOException e) {
            logger.error("error while uploading file",e);
            e.printStackTrace();
            
        }
        
        logger.info("upload success now saving user to db");
        User savedUser=userRepository.save(user);
        System.out.println(savedUser);
        if (savedUser!=null && savedUser.getFirstName().equals(signupCo.getFirstName())){
            logger.info("returing saved user to signup controller");
    
            return savedUser;
        }else{
            return null;
        }
     
    }
}
