package com.ttn.linksharing.service;

import com.ttn.linksharing.entity.User;
import com.ttn.linksharing.repository.UserRepository;
import com.ttn.linksharing.service.impl.UserServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService implements UserServiceInterface {
    
    @Autowired
    UserRepository userRepository;
    
    public User getUserById(Integer userId){
        return userRepository.getUserById(userId);
    }
    
    public List<User> getAllUsers(){
        return userRepository.getAllUsers();
    }
    
    public void saveUser(User userToSave) {
        userRepository.save(userToSave);
    }
    
    
    public User getUserByUsernameAndPassword(String username, String password) {
        return userRepository.getUserByUsernameAndPassword(username, password);
    }
}
