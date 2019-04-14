package com.ttn.linksharing.controller;

import com.ttn.linksharing.Dto.UserDto;
import com.ttn.linksharing.co.PostsCo;
import com.ttn.linksharing.co.TopicsCo;
import com.ttn.linksharing.entity.User;
import com.ttn.linksharing.service.DashboardService;
import com.ttn.linksharing.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;

@Controller
public class AdminController {

    @Autowired
    DashboardService dashboardService;
    
    @Autowired
    UserService userService;
    
    @GetMapping("/admin/manageUser")
    public String manageUser(Model model,
                             HttpSession session){
    
    
        if (session.getAttribute("login")!=null
            && session.getAttribute("login")=="true"){
            
            Integer currentUser=(Integer) session.getAttribute("userId");
            User user =userService.getUserById(currentUser);
    
            if (user.getRole().equals("ADMIN")){
                UserDto userDto = dashboardService.prepareUserDto(user);
                
                model.addAttribute("currentUser",userDto);
                model.addAttribute("topicCo",new TopicsCo());
                model.addAttribute("postsCo",new PostsCo());
                model.addAttribute("allUser",userService.getAllUsers());
    
                return "manageUser";
                
            }else {
                return "redirect:/dashboard";
            }
        }else{
            return "redirect:/dashboard";
        }
    }
    
    @PostMapping("admin/deactivate")
    public String deactivate(@RequestParam("userId") Integer userId,
                             HttpSession session, Model model){
        
        if (session.getAttribute("login")!=null
                && session.getAttribute("login")=="true"){
        
            Integer currentUser=(Integer) session.getAttribute("userId");
            User user =userService.getUserById(currentUser);
            
            UserDto userDto = dashboardService.prepareUserDto(user);
    
            
            User userToDeactivate =userService.getUserById(userId);
            userToDeactivate.setIsActive(0);
            userService.saveUser(userToDeactivate);
            
            model.addAttribute("currentUser",userDto);
            model.addAttribute("topicCo",new TopicsCo());
            model.addAttribute("postsCo",new PostsCo());
            model.addAttribute("allUser",userService.getAllUsers());
            
            return "redirect:/admin/manageUser";
        }else{
            return "redirect:/dashboard";
        }
    }
    
    @PostMapping("admin/activate")
    public String activate(@RequestParam("userId") Integer userId,
                             HttpSession session, Model model){
        
        if (session.getAttribute("login")!=null
                && session.getAttribute("login")=="true"){
            
            Integer currentUser=(Integer) session.getAttribute("userId");
            User user =userService.getUserById(currentUser);
            
            UserDto userDto = dashboardService.prepareUserDto(user);
            
            
            User userToActivate =userService.getUserById(userId);
            userToActivate.setIsActive(1);
            userService.saveUser(userToActivate);
            
            model.addAttribute("currentUser",userDto);
            model.addAttribute("topicCo",new TopicsCo());
            model.addAttribute("postsCo",new PostsCo());
            model.addAttribute("allUser",userService.getAllUsers());
            
            return "redirect:/admin/manageUser";
        }else{
            return "redirect:/dashboard";
        }
    }
    
    @GetMapping("/admin/deactivate")
    public String get_deactivate(){return "redirect:/dashboard";}
    
    @GetMapping("/admin/activate")
    public String get_activate(){return "redirect:/dashboard";}
}
