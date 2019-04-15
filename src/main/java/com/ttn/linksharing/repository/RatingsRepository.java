package com.ttn.linksharing.repository;

import com.ttn.linksharing.entity.Ratings;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface RatingsRepository extends CrudRepository<Ratings,Integer> {

    Ratings getRatingsByUserIdAndPostId(Integer userId,Integer postId);
    
    
    List<Ratings> getAllByPostId(Integer postId);

}
