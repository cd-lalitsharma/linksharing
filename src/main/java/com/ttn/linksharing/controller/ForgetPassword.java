package com.ttn.linksharing.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ForgetPassword {

    @GetMapping("/resetPassword")
    public String forget_password_get_request(){
        
        return "forgetPassword";
    }
}
