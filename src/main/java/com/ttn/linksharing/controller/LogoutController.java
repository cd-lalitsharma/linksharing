package com.ttn.linksharing.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpSession;

@Controller
public class LogoutController {
    
    //invalid the session and redirect user to index page
    @GetMapping("/logout")
    public String logout_get_request(HttpSession session){
        session.setAttribute("login","");
        session.invalidate();
        return "redirect:/";
    }
    
    //if post logout request then redirect to index WITHOUT log out
    @PostMapping("/logout")
    public String logout_post_request(){
        return "redirect:/";
    }
}
