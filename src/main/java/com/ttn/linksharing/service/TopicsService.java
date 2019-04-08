package com.ttn.linksharing.service;

import com.ttn.linksharing.co.TopicsCo;
import com.ttn.linksharing.entity.Topics;
import com.ttn.linksharing.entity.User;
import com.ttn.linksharing.repository.TopicsRepository;
import com.ttn.linksharing.repository.UserRepository;
import com.ttn.linksharing.service.impl.TopicsServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TopicsService implements TopicsServiceInterface {

    @Autowired
    TopicsRepository topicsRepository;
    
    @Autowired
    UserRepository userRepository;
    
    public Topics saveTopic(TopicsCo topicsCo,
                            Integer userId){
        Topics topic = new Topics();
        topic.setName(topicsCo.getTopicName());
        User user =userRepository.getUserById(userId);
        topic.setUser(user);
        //converting enum to string because entity has
        // visibility as enum whereas CO has visibility as enum
        topic.setVisibility(topicsCo.getVisibility().toString());
        topicsRepository.save(topic);
        
        return topic;
    }
}
