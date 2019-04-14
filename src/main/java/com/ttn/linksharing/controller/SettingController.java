package com.ttn.linksharing.controller;


import com.ttn.linksharing.Dto.UserDto;
import com.ttn.linksharing.co.PasswordCo;
import com.ttn.linksharing.co.PostsCo;
import com.ttn.linksharing.co.SettingCo;
import com.ttn.linksharing.co.TopicsCo;
import com.ttn.linksharing.entity.User;
import com.ttn.linksharing.service.DashboardService;
import com.ttn.linksharing.service.SettingService;
import com.ttn.linksharing.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;

@Controller
public class SettingController {
    
    @Autowired
    UserService userService;
    
    @Autowired
    DashboardService dashboardService;
    
    @Autowired
    SettingService settingService;
    
    @GetMapping("/settings")
    public String get_request_settings(Model model,
                                       HttpSession session){
        
        if (session.getAttribute("login")!=null &&
            session.getAttribute("login")=="true"){
            
            Integer currentUserId=(Integer)session.getAttribute("userId");
    
            User currentUser=userService.getUserById(currentUserId);
    
            UserDto userDto = dashboardService.prepareUserDto(currentUser);
            
            model.addAttribute("currentUser",userDto);
            model.addAttribute("settingCo",new SettingCo());
            model.addAttribute("passwordCo",new PasswordCo());
            model.addAttribute("topicCo", new TopicsCo());
            model.addAttribute("postsCo", new PostsCo());
    
    
            return "settings";
            
        }else {
            return "redirect:/dashboard";
        }
    }
    
    
    @PostMapping("/updateProfile")
    public String updateProfile(@RequestParam("firstName") String firstName,
                                @RequestParam("lastName") String lastName,
                                @RequestParam("userName") String userName,
                                HttpSession session,
                                @RequestParam("profilePhoto") MultipartFile multipartFile){
        if (session.getAttribute("login")!=null &&
            session.getAttribute("login")=="true"){
    
            SettingCo settingCo =new SettingCo();
            settingCo.setFirstName(firstName);
            settingCo.setLastName(lastName);
            settingCo.setUserName(userName);
    
    
            Integer userId = (Integer)session.getAttribute("userId");
            User currentUser = userService.getUserById(userId);
    
            settingService.updateProfile(currentUser,settingCo,multipartFile);
    
            return "redirect:/settings";
    
            
            
        }else {
            return "redirect:/";
        }
    }
    
    @PostMapping("/updatePassword")
    public String updatePassword(@ModelAttribute PasswordCo passwordCo,
                               BindingResult bindingResult,
                               HttpSession session){
        if (session.getAttribute("login")!=null &&
                session.getAttribute("login")=="true"){
        
            if (bindingResult.hasErrors()){
                return "redirect:/settings";
            }else{
                Integer userId = (Integer)session.getAttribute("userId");
                User currentUser = userService.getUserById(userId);
    
                settingService.updatePassword(currentUser,passwordCo);
    
                return "redirect:/settings";
            }
        
        }else {
            return "redirect:/";
        }
    }
    
    @GetMapping("/updateProfile")
    public String get_update_profile(){return "redirect:/settings";}
    @GetMapping("/updatePassword")
    public String get_update_password(){return "redirect:/settings";}
    @PostMapping("/settings")
    public String post_request_settings(){return "redirect:/dashboard";}
}
