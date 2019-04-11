package com.ttn.linksharing.repository;

import com.ttn.linksharing.entity.Posts;
import com.ttn.linksharing.entity.Topics;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

public interface ResourceRepository extends CrudRepository<Posts,Integer> {

    Posts save(Posts post);
    
    @Query("select p.topic from Posts p group by p.topic order by count (p.topic) desc")
    List<Topics> trendingTopics();
    
}
