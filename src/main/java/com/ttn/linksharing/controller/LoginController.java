package com.ttn.linksharing.controller;

import com.ttn.linksharing.co.LoginCo;
import com.ttn.linksharing.entity.User;
import com.ttn.linksharing.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@Controller
public class LoginController {
    
    //mapping for login page
    //get request attributes
    //check credentials from login service
    //if valid then set session and redirect to dashboard
    //else set error message and redirect to index page
    
    @Autowired
    UserService userService;
    
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
    
        List<String> loginErrorMessage=new ArrayList<>();
        
        if (bindingResult.hasErrors()){
            bindingResult.getFieldErrors().forEach(e-> loginErrorMessage.add(e.getDefaultMessage()));
            
            session.setAttribute("loginErrorMessage",loginErrorMessage);
            
            return "redirect:/";
        }else{
            //check if user is active and password or username is correct
            String username=loginco.getUsername();
            String password=loginco.getPassword();
            
            User user= userService.getUserByUsernameAndPassword(username,password);
            
            if (user==null){
                System.out.println("user is null");
                loginErrorMessage.add("username or password not found");
                session.setAttribute("loginErrorMessage",loginErrorMessage);
                return "redirect:/";
            }else{
    
                if (user.getIsActive()==1){
                    System.out.println("yes active");
                    session.setAttribute("login","true");
                    session.setAttribute("userId",user.getId());
    
                    return "redirect:/dashboard";
                }else{
                    System.out.println("no active");
                    loginErrorMessage.add("cannot login! your account is disabled");
                    session.setAttribute("loginErrorMessage",loginErrorMessage);
                    return "redirect:/";
                }
            }
        }
    }
    
    //redirect to index page on login GET request
    @GetMapping("/login")
    public String login_get_request(){
        return "redirect:/";
    }
}
