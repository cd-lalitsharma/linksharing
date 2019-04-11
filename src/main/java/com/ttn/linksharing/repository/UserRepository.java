package com.ttn.linksharing.repository;

import com.ttn.linksharing.entity.Subscriptions;
import com.ttn.linksharing.entity.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Set;

public interface UserRepository extends CrudRepository<User,Integer> {


    User save(User user);
    
    User getUserById(Integer id);
    

}
