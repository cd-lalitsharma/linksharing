package com.ttn.linksharing.service;


import com.ttn.linksharing.entity.Ratings;
import com.ttn.linksharing.repository.RatingsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class RatingsService {

    @Autowired
    RatingsRepository ratingsRepository;
    
    public Integer getCurrentPostRating(Integer userId,Integer postId){
        Ratings rating;
        rating=ratingsRepository.getRatingsByUserIdAndPostId(userId,postId);
        
        if (rating==null){
            return 0;
        }else{
            return rating.getRating();
        }
    }
    
    
    public Integer getAccumulativePostRating(Integer postId){
        List<Ratings> ratingsList = ratingsRepository.getAllByPostId(postId);
        
        
        if (ratingsList.size()!=0){
    
            List<Integer> totalRating= new ArrayList<>();
            Integer numberOfRatings=0;
    
            ratingsList.forEach(e->{
                totalRating.add(e.getRating());
            });
    
            Integer rating=totalRating.stream().reduce(0,(init,itr)->init+itr);
    
            return rating/totalRating.size();
        }else{
            return 0;
        }
    }
    
    
    public Ratings getSavedRatings(Integer userId,Integer postId){
        Ratings rating = ratingsRepository.getRatingsByUserIdAndPostId(userId, postId);
        
        return rating;
    }
    
    public void saveRating(Ratings rating){
        ratingsRepository.save(rating);
    }
}
