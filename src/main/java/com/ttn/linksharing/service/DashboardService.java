package com.ttn.linksharing.service;

import com.ttn.linksharing.Dto.UserDto;
import com.ttn.linksharing.entity.Posts;
import com.ttn.linksharing.entity.Subscriptions;
import com.ttn.linksharing.entity.Topics;
import com.ttn.linksharing.entity.User;
import com.ttn.linksharing.repository.*;
import com.ttn.linksharing.service.impl.DashboardServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

@Service
public class DashboardService implements DashboardServiceInterface {

    @Autowired
    TopicsRepository topicsRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    ResourceRepository resourceRepository;
    
    @Autowired
    ReadPostsRepository readPostsRepository;
    
    @Autowired
    SubscriptionRepository subscriptionRepository;
 
    
    //get current user
    public User getCurrentUser(Integer userId){
        return userRepository.getUserById(userId);
    }
    
    public List<Subscriptions> getUserSubscriptions(Integer userId){
       return subscriptionRepository.findSubscriptionsByUserId(userId);
    }
    
    public List<Topics> trendingTopics(){
        
        return resourceRepository.trendingTopics();
    }
    
    
    public List<Posts> getUnreadPosts(User currentUser){
        
        
        List<Posts> postsList = new LinkedList<>();
        //get subscriptions of user
        List<Subscriptions> subscriptionsList=subscriptionRepository.findSubscriptionsByUserId(currentUser.getId());
    
        for (Subscriptions subscription:
             subscriptionsList) {
    
            Topics topic = subscription.getTopic();
    
            for (Posts post:
                 topic.getposts()) {
                if (readPostsRepository.countByUserAndPost(currentUser,post)==0){
                    postsList.add(post);
                }
            }
        }
        
        return postsList;
    }
    
    public UserDto prepareUserDto(User currentUser) {
        UserDto userDto =new UserDto();
        List<Integer> unreadPostsId= new ArrayList<>();
        
        userDto.setId(currentUser.getId());
        userDto.setEmail(currentUser.getEmail());
        userDto.setFirstName(currentUser.getFirstName());
        userDto.setLastName(currentUser.getLastName());
        System.out.println("user profile is"+currentUser.getPhoto());
        System.out.println("user profile is"+currentUser.getPhoto());
        userDto.setPhoto(currentUser.getPhoto());
        userDto.setFacebook(currentUser.getFacebook());
        userDto.setInstagram(currentUser.getInstagram());
        userDto.setTwitter(currentUser.getTwitter());
        userDto.setRole(currentUser.getRole());
        userDto.setUsername(currentUser.getUsername());
        userDto.getTopics().addAll(currentUser.gettopics());
        userDto.setSubscribedTopicId(this.getSubscribedTopicId(currentUser.getId()));
        userDto.setUnreadPosts(this.getUnreadPosts(currentUser));
    
        userDto.getUnreadPosts().forEach(e->unreadPostsId.add(e.getId()));
        userDto.setUnreadPostsId(unreadPostsId);
        
        userDto.setSubscriptions(this.getUserSubscriptions(currentUser.getId()));
        
        return userDto;
    }
    
    public List<Integer> getSubscribedTopicId(Integer userId) {
        List<Integer> subscribedTopicId= new ArrayList<>();
        this.getUserSubscriptions(userId).forEach(e->subscribedTopicId.add(e.getTopic().getId()));
        return subscribedTopicId;
    }
}
