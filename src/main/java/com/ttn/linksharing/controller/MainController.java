package com.ttn.linksharing.controller;

import com.ttn.linksharing.co.LoginCo;
import com.ttn.linksharing.co.SignupCo;
import com.ttn.linksharing.service.ResourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Controller
public class MainController {
    
    @Autowired
    ResourceService resourceService;
    
    @GetMapping("*")
    public String handle_404(HttpServletResponse response , HttpSession session) {
//        response.sendRedirect("http://www.google.com");
//        return "redirect:http://www.google.com";
//        session.setAttribute("logged_in","true");
        return "404";
    }
    
    //mapping for index page
    @GetMapping("/")
    public String index(Model model,
                        HttpSession session){
        if (session.getAttribute("login")=="true"
            && !session.getAttribute("login").toString().isEmpty()){
            
            return "redirect:/dashboard";
        }else{
            model.addAttribute("loginco",new LoginCo());
            model.addAttribute("signupco",new SignupCo());
            
            model.addAttribute("featuredPosts",resourceService.getTrendingPosts());
            model.addAttribute("trendingPosts",resourceService.getFeaturedPosts());
            return "index";
        }
        
    }
    
    
    
    
    
}
