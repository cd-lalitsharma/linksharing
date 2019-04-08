package com.ttn.linksharing.controller;

import com.ttn.linksharing.co.SignupCo;
import com.ttn.linksharing.co.TopicsCo;
import com.ttn.linksharing.entity.User;
import com.ttn.linksharing.service.SignupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@Controller
public class SignupController {
    public static String photoUploadDir=System.getProperty("user.dir")+"/uploads/profilePhotos/";
    @Autowired
    SignupService signupService;
    
    @PostMapping("/signup")
    public String post_signup_request(@Valid @ModelAttribute SignupCo signupCo,
                                      BindingResult bindingResult,
                                      HttpSession session,
                                      @RequestParam("photo") MultipartFile multipartFile){
        System.out.println(signupCo);
        //checking for errors in request
        if (bindingResult.hasErrors()){
            bindingResult.getFieldErrors().forEach(e-> System.out.println(e));
            
            //add errors to modal and show them on index page
            return "redirect:/";
            
        }else{
            //no errors in request;
            //call saveUser from signupservice
    
            //if saved successfully it will return a user otherwise null
            User savedUser=signupService.saveUser(signupCo,multipartFile,photoUploadDir);
            
            if (savedUser!=null){
                
                //set session login = true
                //set set user id in session
                //redirect to dashboard
                
                session.setAttribute("login","true");
                session.setAttribute("userId",savedUser.getId());
                
                return "redirect:/dashboard";
                
            }
            
            //if saved user is null then error while saving user
            //redirect user to index and show error while saving
            return "redirect:/";
            
        }
        
    }
    
    //redirect to index page on GET signup request
    @GetMapping("/signup")
    public String get_signup_request(){
        return "redirect:/";
    }
}
