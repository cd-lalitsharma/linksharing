package com.ttn.linksharing.repository;

import com.ttn.linksharing.entity.Topics;
import com.ttn.linksharing.entity.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TopicsRepository extends CrudRepository<Topics,Integer> {
    
    Topics save(Topics topics);
    
    Topics getTopicsById(Integer topicId);
    
    //to pass var of linked colmns we need to pass whole entity
    @Query("select t from Topics t where t.user=:user")
    List<Topics> findTopicByUser(@Param("user") User user);
    
    @Query("SELECT t FROM Topics  t WHERE t.name LIKE CONCAT('%',:search_term,'%')")
    List<Topics> searchTopicName( @Param("search_term")String search_term);
    
    
    
}
