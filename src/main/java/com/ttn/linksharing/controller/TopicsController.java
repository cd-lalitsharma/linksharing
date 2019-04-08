package com.ttn.linksharing.controller;

import com.ttn.linksharing.co.TopicsCo;
import com.ttn.linksharing.service.TopicsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

@Controller
public class TopicsController {
    
    @Autowired
    TopicsService topicsService;
    
    @PostMapping("/createTopic")
    @ResponseBody
    public String createTopic(@ModelAttribute TopicsCo topicsCo,
                              BindingResult bindingResult,
                              HttpSession session){
        Integer userId=(Integer) session.getAttribute("userId");
        
        topicsService.saveTopic(topicsCo,userId);
    
        System.out.println(topicsCo.getTopicName());
        System.out.println(topicsCo.getVisibility());
        
        return "chal gao";
    }
}
