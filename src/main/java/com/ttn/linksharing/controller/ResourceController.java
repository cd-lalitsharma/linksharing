package com.ttn.linksharing.controller;

import com.ttn.linksharing.co.PostsCo;
import com.ttn.linksharing.entity.Posts;
import com.ttn.linksharing.entity.Resources;
import com.ttn.linksharing.enums.ResourceEnum;
import com.ttn.linksharing.service.ResourceService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
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
}
