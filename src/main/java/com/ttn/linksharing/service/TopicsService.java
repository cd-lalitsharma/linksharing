package com.ttn.linksharing.service;

import com.ttn.linksharing.co.TopicsCo;
import com.ttn.linksharing.entity.Subscriptions;
import com.ttn.linksharing.entity.Topics;
import com.ttn.linksharing.entity.User;
import com.ttn.linksharing.enums.SeriousnessEnum;
import com.ttn.linksharing.repository.SubscriptionRepository;
import com.ttn.linksharing.repository.TopicsRepository;
import com.ttn.linksharing.repository.UserRepository;
import com.ttn.linksharing.service.impl.TopicsServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class TopicsService implements TopicsServiceInterface {

    @Autowired
    TopicsRepository topicsRepository;
    
    @Autowired
    UserRepository userRepository;
    
    @Autowired
    SubscriptionRepository subscriptionRepository;
    
    public Topics saveTopic(TopicsCo topicsCo,
                            Integer userId){
        Topics topic = new Topics();
        topic.setName(topicsCo.getTopicName());
        User user =userRepository.getUserById(userId);
        topic.setUser(user);
        //converting enum to string because entity has
        // visibility as enum whereas CO has visibility as string
        topic.setVisibility(topicsCo.getVisibility().toString());
        
        Subscriptions subscription = new Subscriptions();
        subscription.setTopic(topic);
        subscription.setUser(user);
        subscription.setTopicSeriousness(SeriousnessEnum.CASUAL.toString());
        
        topic.getSubscription().add(subscription);
        
        topicsRepository.save(topic);
        
        return topic;
    }
    
    public String subscribeTopic(Integer userId, Integer topicId) {
        
        User user=userRepository.getUserById(userId);
        Topics topic = topicsRepository.getTopicsById(topicId);
        
        Subscriptions subscription = new Subscriptions();
        subscription.setUser(user);
        subscription.setTopicSeriousness(SeriousnessEnum.CASUAL.toString());
        subscription.setTopic(topic);
        
        if (topic.getSubscription().contains(subscription)){
            System.out.println("cannot sub already exists");
            return "subscription already exists";
        }else{
    
            topic.getSubscription().add(subscription);
            Topics updatedTopic=topicsRepository.save(topic);
            
            return "success";
        }
        
        
    }
    
    @Transactional
    public String unsubscribeTopic(Integer userId, Integer topicId) {
        
        System.out.println("getting subscription of user to unfollow");
        Integer result= subscriptionRepository.deleteSubscriptionsByUserAndTopic(userId,topicId);
    
        if (result!=null){
            System.out.println(result +" is sresult ");
            System.out.println("removing subscription of user");
            
            return "success";
        }else {
            System.out.println("cannot unsub not already exists");
    
            return "no subscription exists to unsubscribe";
            
        }
    }
    
    public Topics getTopicById(Integer topicId) {
        
        return topicsRepository.getTopicsById(topicId);
    }
    
    public void deleteTopic(Integer topicId) {
        
        topicsRepository.deleteById(topicId);
 
    }
    
    public void delete(Topics topic){
        topicsRepository.delete(topic);
    }
    
    public Boolean changeTopicSeriousness(Integer subscriptionId,String choosedSeriousnes) {
    
        Subscriptions subscription = subscriptionRepository.findSubscriptionsById(subscriptionId);
       
        String selectedSeriousness =SeriousnessEnum.valueOf(choosedSeriousnes).toString();
        
        if (selectedSeriousness.isEmpty()){
            return false;
        }else{
            subscription.setTopicSeriousness(selectedSeriousness);
            subscriptionRepository.save(subscription);
            return true;
        }
        
        
        
        
    }
}
