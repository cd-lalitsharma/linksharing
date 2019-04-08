package com.ttn.linksharing.repository;

import com.ttn.linksharing.entity.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User,Integer> {


    User save(User user);
    
    User getUserById(Integer id);

}
