package com.tuck.shop.users.service;

import com.tuck.shop.users.entity.User;
import com.tuck.shop.users.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Tinashe on 17/7/2023
 */
@Service
public class UserRegistrationService {

    @Autowired
    private UserRepository userRepository;

    public User registerUser(User user){
        if (user.getPhoneNumber() == null || user.getPhoneNumber().isEmpty()){
            throw new IllegalArgumentException("Phone number cannot be empty.");
        }
        return userRepository.save(user);
    }
}
