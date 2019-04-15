package com.ttn.linksharing.repository;

import com.ttn.linksharing.entity.Posts;
import com.ttn.linksharing.entity.Topics;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ResourceRepository extends CrudRepository<Posts,Integer> {

    Posts save(Posts post);
    
    @Query("select p.topic from Posts p group by p.topic order by count (p.topic) desc")
    List<Topics> trendingTopics();
    
    Posts getPostsById(Integer postId);
    
    List<Posts> findAllByOrderByRatingsDesc();
    
    @Query("select  p from Posts p order by Id asc ")
    List<Posts> findAllByOrderByIdAsc();
    
    @Query("SELECT p FROM Posts  p WHERE p.description LIKE CONCAT('%',:search_term,'%')")
    List<Posts> searchPostDescription(@Param("search_term")String search_term);
    
    @Query("SELECT p FROM Posts  p WHERE p.title LIKE CONCAT('%',:search_term,'%')")
    List<Posts> searchPostTitle(@Param("search_term") String search_term);
    
}
