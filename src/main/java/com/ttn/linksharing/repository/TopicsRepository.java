package com.ttn.linksharing.repository;

import com.ttn.linksharing.entity.Topics;
import com.ttn.linksharing.entity.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TopicsRepository extends CrudRepository<Topics,Integer> {
    
    Topics save(Topics topics);
    
    //to pass var of linked colmns we need to pass whole entity
    @Query("select t from Topics t where t.user=:user")
    List<Topics> findTopicByUser(@Param("user") User user);
    
}
