package com.ttn.linksharing.controller;


import com.ttn.linksharing.entity.Posts;
import com.ttn.linksharing.entity.Ratings;
import com.ttn.linksharing.entity.User;
import com.ttn.linksharing.service.DashboardService;
import com.ttn.linksharing.service.RatingsService;
import com.ttn.linksharing.service.ResourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

@Controller
public class RatingsController {
    
    @Autowired
    RatingsService ratingsService;
    
    @Autowired
    DashboardService dashboardService;
    
    @Autowired
    ResourceService resourceService;
    
    @PostMapping("/ratePost")
    @ResponseBody
    public String ratePost(@RequestParam("postId") Integer postId,
                           @RequestParam("rating") Integer rating,
                           HttpSession session){
    
        if (session.getAttribute("login")!=null &&
            session.getAttribute("login")=="true"){
            Integer currentUserId = (Integer) session.getAttribute("userId");
            Ratings ratingObj = new Ratings();
            
            Ratings savedRating= ratingsService.getSavedRatings(currentUserId,postId);
            
            if (savedRating==null){
                User currentUser = dashboardService.getCurrentUser(currentUserId);
                Posts currentPost = resourceService.getPostById(postId);
    
                ratingObj.setRating(rating);
                ratingObj.setuser(currentUser);
                ratingObj.setpost(currentPost);
                
                ratingsService.saveRating(ratingObj);
            }else{
                savedRating.setRating(rating);
                ratingsService.saveRating(savedRating);
            }
            
            return "success";
            
        }else {
            return "error";
        }
    }
}
