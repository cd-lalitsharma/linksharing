package com.ttn.linksharing.controller;

import com.ttn.linksharing.Dto.UserDto;
import com.ttn.linksharing.co.PostsCo;
import com.ttn.linksharing.co.TopicsCo;
import com.ttn.linksharing.entity.User;
import com.ttn.linksharing.repository.TopicsRepository;
import com.ttn.linksharing.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpSession;

@Controller
public class UserController {
    
    @Autowired
    TopicsRepository topicsRepository;
    @Autowired
    UserRepository userRepository;
    
    
    //show dashboard of user
    //only if logged in and session is set
    //if not then redirect to index page
    @GetMapping("/dashboard")
    public String dashboard_get_request(HttpSession session, Model model){
        
        if (session.getAttribute("login")=="true"){
            
            //Getting current user id from session
            Integer currentUserId = (Integer) session.getAttribute("userId");
            System.out.println("user id =======>>>>"+currentUserId);
            //getting current user from database
            User currentUser = userRepository.getUserById(currentUserId);
            System.out.println("topipcs list"+currentUser.gettopics());
            //setting currentUser
            
            model.addAttribute("topicCo",new TopicsCo());
            model.addAttribute("postsCo",new PostsCo());
            model.addAttribute("topicsCreatedByUser",currentUser.gettopics());
            
            
            
            //send user dto to dashboard to get functionality
            model.addAttribute("currentUser",currentUser);
            System.out.println("working till here");
//            System.out.println("t list"+ topicsRepository.findTopicByUser(userRepository.getUserById(1)));
            
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
