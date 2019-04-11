package com.ttn.linksharing.repository;

import com.ttn.linksharing.entity.Posts;
import com.ttn.linksharing.entity.ReadPosts;
import com.ttn.linksharing.entity.User;
import org.springframework.data.repository.CrudRepository;

public interface ReadPostsRepository extends CrudRepository<ReadPosts,Integer> {

    Integer countByUserAndPost(User user, Posts post);
}
