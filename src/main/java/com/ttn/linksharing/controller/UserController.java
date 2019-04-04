package com.ttn.linksharing.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpSession;

@Controller
public class UserController {
    
    
    //show dashboard of user
    //only if logged in and session is set
    //if not then redirect to index page
    @GetMapping("/dashboard")
    public String dashboard_get_request(HttpSession session){
        
        if (session.getAttribute("login")=="true"){
            
            return "dashboard";
    
        }else{
         return "redirect:/";
        }
    }
    
    @PostMapping("/dashboard")
    public String dashboard_post_request(){
        return "redirect:/";
    }
}
