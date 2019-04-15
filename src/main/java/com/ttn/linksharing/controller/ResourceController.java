package com.ttn.linksharing.controller;

import com.ttn.linksharing.Dto.UserDto;
import com.ttn.linksharing.co.PostsCo;
import com.ttn.linksharing.co.TopicsCo;
import com.ttn.linksharing.entity.Posts;
import com.ttn.linksharing.entity.ReadPosts;
import com.ttn.linksharing.entity.Resources;
import com.ttn.linksharing.entity.User;
import com.ttn.linksharing.enums.ResourceEnum;
import com.ttn.linksharing.service.DashboardService;
import com.ttn.linksharing.service.RatingsService;
import com.ttn.linksharing.service.ResourceService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

@Controller
public class ResourceController {
    
    private static final Logger logger= LoggerFactory.getLogger(ResourceController.class);
    @Autowired
    ResourceService resourceService;
    
    @Autowired
    DashboardService dashboardService;
    
    @Autowired
    RatingsService ratingsService;
    
    @PostMapping("/createLinkResource")
    @ResponseBody
    public String create_link_resource(@ModelAttribute PostsCo postsCo,
                                       BindingResult bindingResult,
                                       HttpSession session){
        Integer userId = (Integer)session.getAttribute("userId");
        if (bindingResult.hasErrors()){
            
            //handle
        }
        
        logger.info("sending request to resource service to save post");
        
        Posts post=resourceService.saveLinkPost(postsCo,userId);
        
        if (post!=null){
            logger.info("post is saved successfully");
    
            return "successfully saved";
        }else{
            logger.info("error saving in post");
            
            return "error in storing post";
        }
    }
    
    @GetMapping("/createLinkResource")
    public String get_create_link_resource(){
        return "redirect:/dashboard";
    }
    
    @PostMapping("/createDocumentResource")
    @ResponseBody
    public String post_create_document_resource(@ModelAttribute PostsCo postsCo,
                                                @RequestParam("document_resource_file") MultipartFile multipartFile,
                                                HttpSession session){
    
        Integer userId = (Integer)session.getAttribute("userId");
    
    
        logger.info("sending request to upload document and save post form resource controller");
        Posts post=resourceService.saveDocumentPost(postsCo,multipartFile,userId);
        
        if (post.getTitle().equals(postsCo.getPostName())){
            return "success";
        }else{
            return "error";
        }
       
        
        
        
    }
    
    @GetMapping("/createDocumentResource")
    @ResponseBody
    public String get_create_document_resource(){
        return "redirect:/dashboard";
    }
    
    
    @PostMapping("/markPostAsRead")
    @ResponseBody
    public String markPostAsRead(@RequestParam("postId") Integer postId,
                                 HttpSession session){
        if (session.getAttribute("login")!=null &&
            session.getAttribute("login")=="true"){
            
            Integer userId=(Integer)session.getAttribute("userId");
            ReadPosts readPost=resourceService.markPostAsRead(userId,postId);
            
            if (readPost.getpost().getId()==postId){
                return "success";
            }
        }else{
            return "error";
        }
        
        return null;
    }
    
    @GetMapping("/markPostAsRead")
    public String get_markPostAsRead(){return "redirect:/dashboard";    }
    
    
    @GetMapping("/post/id/{id}")
    public String showPost(@PathVariable("id") Integer postId,
                           Model model,
                           HttpSession session){
        
        if (session.getAttribute("login")!=null
            && session.getAttribute("login")=="true"){
            
            Integer currentUserId = (Integer) session.getAttribute("userId");
            User currentUser = dashboardService.getCurrentUser(currentUserId);
            UserDto currentUserDto = dashboardService.prepareUserDto(currentUser);
            currentUserDto.setCurrentPostRating(ratingsService.getCurrentPostRating(currentUserId,postId));
            model.addAttribute("currentUser",currentUserDto);
            model.addAttribute("topicCo",new TopicsCo());
            model.addAttribute("postsCo",new PostsCo());
            session.setAttribute("specificPostRating",ratingsService.getCurrentPostRating(currentUserId,postId));
    
    
        }
        if (session.getAttribute("specificPostRating")==null){
            session.setAttribute("specificPostRating",0);
    
        }
        model.addAttribute("trendingTopics",dashboardService.trendingTopics());
        session.setAttribute("accumulativePostRating",ratingsService.getAccumulativePostRating(postId));
        session.setAttribute("postId",postId);
        Posts requestedPost = resourceService.getPostById(postId);
        
        model.addAttribute("post",requestedPost);
        
        return "viewPost";
    }
    
}
