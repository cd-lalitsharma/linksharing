package com.ttn.linksharing.service;

import com.ttn.linksharing.entity.Posts;
import com.ttn.linksharing.entity.Topics;
import com.ttn.linksharing.repository.ResourceRepository;
import com.ttn.linksharing.repository.TopicsRepository;
import com.ttn.linksharing.service.impl.SearchServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
public class SearchService implements SearchServiceInterface {
    @Autowired
    ResourceRepository resourceRepository;
    
    @Autowired
    TopicsRepository topicsRepository;
    
    public List<Posts> searchPosts(String search_term) {
        List<Posts> postsList= new ArrayList<>();
        if (resourceRepository.searchPostDescription(search_term)!=null){
            postsList.addAll(resourceRepository.searchPostDescription(search_term));
        }
        
        if (resourceRepository.searchPostTitle(search_term)!=null){
            postsList.addAll(resourceRepository.searchPostTitle(search_term));
        }
        
        return postsList;
    }

    public List<Topics> searchTopics(String search_term) {
        return topicsRepository.searchTopicName(search_term);
    }

    
    
}
