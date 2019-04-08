package com.ttn.linksharing.repository;

import com.ttn.linksharing.entity.Topics;
import org.springframework.data.repository.CrudRepository;

public interface TopicsRepository extends CrudRepository<Topics,Integer> {
    
    Topics save(Topics topics);
}
