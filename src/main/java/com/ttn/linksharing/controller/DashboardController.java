package com.ttn.linksharing.controller;


import com.ttn.linksharing.Dto.UserDto;
import com.ttn.linksharing.co.PostsCo;
import com.ttn.linksharing.co.TopicsCo;
import com.ttn.linksharing.entity.Posts;
import com.ttn.linksharing.entity.Subscriptions;
import com.ttn.linksharing.entity.Topics;
import com.ttn.linksharing.entity.User;
import com.ttn.linksharing.repository.TopicsRepository;
import com.ttn.linksharing.repository.UserRepository;
import com.ttn.linksharing.service.DashboardService;
import com.ttn.linksharing.service.ResourceService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class DashboardController {
    
    @Autowired
    DashboardService dashboardService;
    
    private final static Logger logger = LoggerFactory.getLogger(DashboardController.class);
    //show dashboard of user
    //only if logged in and session is set
    //if not then redirect to index page
    @GetMapping("/dashboard")
    public String dashboard_get_request(HttpSession session, Model model) {
        
        if (session.getAttribute("login") == "true") {
    
    
            //Getting current user id from session
            Integer currentUserId = (Integer) session.getAttribute("userId");
            logger.info("user with user id"+currentUserId+"send request to dashboard");
    
            //getting current user from database
            User currentUser = dashboardService.getCurrentUser(currentUserId);
            
            //prepare UserDto
            UserDto userDto = dashboardService.prepareUserDto(currentUser);
     
            //sending trending topics to dashboard
            List<Topics> trendingTopics=dashboardService.trendingTopics();
    
            
            model.addAttribute("topicCo", new TopicsCo());
            model.addAttribute("postsCo", new PostsCo());
            model.addAttribute("currentUser", userDto);
            model.addAttribute("trendingTopics",trendingTopics);
    
    
            return "dashboard";
            
        } else {
            return "redirect:/";
        }
    
        
    }
    
    @PostMapping("/dashboard")
    public String dashboard_post_request() {
        return "redirect:/";
    }
    
    
    
}
