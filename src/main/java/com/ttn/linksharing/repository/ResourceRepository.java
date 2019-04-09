package com.ttn.linksharing.repository;

import com.ttn.linksharing.entity.Posts;
import org.springframework.data.repository.CrudRepository;

public interface ResourceRepository extends CrudRepository<Posts,Integer> {

    Posts save(Posts post);
}
