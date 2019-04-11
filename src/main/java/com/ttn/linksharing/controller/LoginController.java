package com.ttn.linksharing.controller;

import com.ttn.linksharing.co.LoginCo;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@Controller
public class LoginController {
    
    //mapping for login page
    //get request attributes
    //check credentials from login service
    //if valid then set session and redirect to dashboard
    //else set error message and redirect to index page
    @PostMapping("/login")
    public String login_post_request(@Valid @ModelAttribute("loginCo") LoginCo loginco,
                        BindingResult bindingResult,
                        Model model,
                        HttpSession session){
        //redirect to index if someone send null post request
        //possible from postman
        if (loginco.getPassword().isEmpty() && loginco.getUsername().isEmpty()){
            return "redirect:/";
        }
        
        if (bindingResult.hasErrors()){
            bindingResult.getFieldErrors().forEach(e-> System.out.println(e.getField()));
            
            
            return "redirect:/";
        }else{
            //check if user is active and password or username is correct
            session.setAttribute("login","true");
            session.setAttribute("userId",2);
    
            return "redirect:/dashboard";
        }
    }
    
    //redirect to index page on login GET request
    @GetMapping("/login")
    public String login_get_request(){
        return "redirect:/";
    }
}
