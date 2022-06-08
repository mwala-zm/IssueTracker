package com.zechariah.issuetrackerdal.service;

import com.zechariah.issuetrackerdal.model.User;
import com.zechariah.issuetrackerdal.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    @Autowired
    UserRepository userRepository;

    public User createUser(User user){
        return userRepository.save(user);
    }

    public User updateUser(Long userId, User userDetails){
        User user = userRepository.findById(userId).get();
        user.setUsername(userDetails.getUsername());
        user.setRole(userDetails.getRole());

        return userRepository.save(user);
    }

    public List<User> getUsers(){
        return userRepository.findAll();
    }

    public void removeUser(Long userId){
        userRepository.deleteById(userId);
    }

}
