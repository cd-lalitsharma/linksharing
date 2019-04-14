package com.ttn.linksharing.controller;

import com.ttn.linksharing.Dto.UserDto;
import com.ttn.linksharing.co.PostsCo;
import com.ttn.linksharing.co.TopicsCo;
import com.ttn.linksharing.entity.Topics;
import com.ttn.linksharing.entity.User;
import com.ttn.linksharing.service.DashboardService;
import com.ttn.linksharing.service.TopicsService;
import com.ttn.linksharing.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

@Controller
public class TopicsController {
    
    @Autowired
    TopicsService topicsService;
    
    @Autowired
    UserService userService;
    
    @Autowired
    DashboardService dashboardService;
    
    @PostMapping("/createTopic")
    @ResponseBody
    public String createTopic(@ModelAttribute TopicsCo topicsCo,
                              BindingResult bindingResult,
                              HttpSession session){
        Integer userId=(Integer) session.getAttribute("userId");
        
        topicsService.saveTopic(topicsCo,userId);
    
        System.out.println(topicsCo.getTopicName());
        System.out.println(topicsCo.getVisibility());
        
        return "success";
    }
    
    
    @PostMapping("/subscribeTopic")
    @ResponseBody
    public String post_subscribeTopic(@RequestParam("topicId") Integer topicId,
                                      HttpSession session){
        if (session.getAttribute("login")!=null &&
                session.getAttribute("login")=="true"){
             
            Integer userId=(Integer) session.getAttribute("userId");
    
            String result=topicsService.subscribeTopic(userId,topicId);
            
            return result;
        }else{
            return "error";
        }
     
        
    }
    
    @GetMapping("/subscribeTopic")
    public String get_subscribeTopic(){ return "redirect:/dashboard";}
    
    @PostMapping("/unsubscribeTopic")
    @ResponseBody
    public String post_unsubscribeTopic(@RequestParam("topicId") Integer topicId,
                                      HttpSession session){
        if (session.getAttribute("login")!=null &&
                session.getAttribute("login")=="true"){
            
            Integer userId=(Integer) session.getAttribute("userId");
            
            String result =topicsService.unsubscribeTopic(userId,topicId);
            
            return result;
        }else{
            return "error";
        }
    }
    
    @GetMapping("/unsubscribeTopic")
    public String get_unsubscribeTopic(){ return "redirect:/dashboard";}
    
    
    @GetMapping("/topic/id/{topicId}")
    public String viewTopic(@PathVariable("topicId") Integer topicId,
                            HttpSession session,
                            Model model){
        
        
        if (session.getAttribute("login")!=null &&
            session.getAttribute("login")=="true"){
            
            Integer userId = (Integer) session.getAttribute("userId");
            User currentUser=dashboardService.getCurrentUser(userId);
            
            UserDto userDto=dashboardService.prepareUserDto(currentUser);
            
            
            model.addAttribute("currentUser",userDto);
            model.addAttribute("topicCo",new TopicsCo());
            model.addAttribute("postsCo",new PostsCo());
     
        }
        
        Topics topic = topicsService.getTopicById(topicId);
        model.addAttribute("topic",topic);
    
        return "viewTopic";
    }
    
}
