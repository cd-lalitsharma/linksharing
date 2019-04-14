package com.ttn.linksharing.service;


import com.ttn.linksharing.co.PasswordCo;
import com.ttn.linksharing.co.SettingCo;
import com.ttn.linksharing.entity.User;
import com.ttn.linksharing.repository.UserRepository;
import com.ttn.linksharing.service.impl.SettingServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Service
public class SettingService implements SettingServiceInterface {

    @Autowired
    UserRepository userRepository;
    
    public static String photoUploadDir=System.getProperty("user.dir")+"/uploads/profilePhotos/";
    
    public void updateProfile(User user,
                                SettingCo settingCo,
                                MultipartFile multipartFile){
        
        User tempUser = new User();
        
        if (!multipartFile.isEmpty()){
            try {
                byte[] bytes= multipartFile.getBytes();
                Path path= Paths.get(photoUploadDir+multipartFile.getOriginalFilename());
                Files.write(path,bytes);
                //store only filename
                String photoPath="uploads/profilePhotos/"+multipartFile.getOriginalFilename();
                tempUser.setPhoto(photoPath);
        
            } catch (IOException e) {
                e.printStackTrace();
        
            }
    
        }
        tempUser.setUsername(settingCo.getUserName());
        tempUser.setFirstName(settingCo.getFirstName());
        tempUser.setLastName(settingCo.getLastName());
        
        if (tempUser.getPhoto().isEmpty()){
            System.out.println(tempUser.getPhoto());
            
            tempUser.setPhoto(user.getPhoto());
        }
        
        tempUser.setId(user.getId());
        tempUser.setPassword(user.getPassword());
        tempUser.setIsActive(user.getIsActive());
        tempUser.setCreatedDate(user.getCreatedDate());
        tempUser.setEmail(user.getEmail());
        tempUser.setRole(user.getRole());
     
        user.setId(tempUser.getId());
        user.setFirstName(tempUser.getFirstName());
        user.setLastName(tempUser.getLastName());
        user.setEmail(tempUser.getEmail());
        user.setCreatedDate(tempUser.getCreatedDate());
        user.setUsername(tempUser.getUsername());
        user.setPassword(tempUser.getPassword());
        user.setIsActive(tempUser.getIsActive());
        user.setPhoto(tempUser.getPhoto());
        
        
        userRepository.save(user);
        
    }
    
    public void updatePassword(User user, PasswordCo passwordCo){
        
        user.setPassword(passwordCo.getPassword());
        
        userRepository.save(user);
    }
}
