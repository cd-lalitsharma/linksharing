package com.ttn.linksharing.controller;

import com.ttn.linksharing.service.ForgetPasswordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;

@Controller
public class ForgetPassword {
    
    @Autowired
    ForgetPasswordService forgetPasswordService;
    
    @GetMapping("/resetPassword")
    public String forget_password_get_request(){
        
        return "forgetPassword";
    }
    
    @PostMapping("/reset")
    public String resetPassword(@RequestParam("email") String email,
                                HttpSession session){
        
        if (forgetPasswordService.checkForEmailExistance(email)){
            
            forgetPasswordService.resetPassword(email);
            session.setAttribute("forgetPasswordRequestStatus","success");
            return "redirect:/resetPassword";
    
        }else{
            session.setAttribute("forgetPasswordRequestStatus","notExists");
    
            return "redirect:/resetPassword";
    
        }
    }
}
