package com.ttn.linksharing.controller;


import com.ttn.linksharing.Dto.UserDto;
import com.ttn.linksharing.co.PostsCo;
import com.ttn.linksharing.co.TopicsCo;
import com.ttn.linksharing.entity.Posts;
import com.ttn.linksharing.entity.Topics;
import com.ttn.linksharing.entity.User;
import com.ttn.linksharing.service.DashboardService;
import com.ttn.linksharing.service.SearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class SearchController {
    
    @Autowired
    DashboardService dashboardService;
    
    @Autowired
    SearchService searchService;
    
    @GetMapping("/search")
    public String search(
            @RequestParam(required = false, defaultValue = "/", value="search_term") String search_term,
            HttpSession session,
            Model model
    ){
        if (session.getAttribute("login") == "true") {
        
        
            //Getting current user id from session
            Integer currentUserId = (Integer) session.getAttribute("userId");
            
            //getting current user from database
            User currentUser = dashboardService.getCurrentUser(currentUserId);
        
            //prepare UserDto
            UserDto userDto = dashboardService.prepareUserDto(currentUser);
            
            List<Posts> postsList= searchService.searchPosts(search_term);
            List<Topics> topicsList=searchService.searchTopics(search_term);
        
            model.addAttribute("topicCo", new TopicsCo());
            model.addAttribute("postsCo", new PostsCo());
            model.addAttribute("currentUser", userDto);
    
    
            model.addAttribute("postList",postsList);
            model.addAttribute("topicList",topicsList);
        
            return "searchPage";
        
        } else {
            
           List<Posts> postsList= searchService.searchPosts(search_term);
           List<Topics> topicsList=searchService.searchTopics(search_term);
           
           model.addAttribute("postList",postsList);
           model.addAttribute("topicList",topicsList);
            return "searchPage";
        }
        
        
    }
}
