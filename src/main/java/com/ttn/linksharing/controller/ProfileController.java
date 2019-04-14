package com.ttn.linksharing.controller;


import com.ttn.linksharing.Dto.UserDto;
import com.ttn.linksharing.co.PostsCo;
import com.ttn.linksharing.co.TopicsCo;
import com.ttn.linksharing.entity.Topics;
import com.ttn.linksharing.entity.User;
import com.ttn.linksharing.service.DashboardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;

@Controller
public class ProfileController {
    
    @Autowired
    DashboardService dashboardService;

    @GetMapping("/user/id/{id}")
    public String showUser(@PathVariable("id") Integer userId,
                           Model model,
                           HttpSession session){
        
        if (session.getAttribute("login")!=null &&
                session.getAttribute("login")=="true"){
        
            Integer currentUserId = (Integer) session.getAttribute("userId");
            User currentUser=dashboardService.getCurrentUser(currentUserId);
    
            UserDto userDto=dashboardService.prepareUserDto(currentUser);
            model.addAttribute("currentUser",userDto);
            model.addAttribute("topicCo",new TopicsCo());
            model.addAttribute("postsCo",new PostsCo());
        
        }
        //if current user and requesteed user are same then return current user
        
        
        User requestedUser = dashboardService.getCurrentUser(userId);
        UserDto requestedUserDto = dashboardService.prepareUserDto(requestedUser);
    
        System.out.println(requestedUser.getposts());
        
        model.addAttribute("requestedUser",requestedUserDto);
    
        return "showUser";
    }
}
