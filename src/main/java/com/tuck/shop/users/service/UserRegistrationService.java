package com.tuck.shop.users.service;

import com.tuck.shop.users.entity.Users;
import com.tuck.shop.users.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

/**
 * @author Tinashe on 17/7/2023
 */
@Service
public class UserRegistrationService {

    @Autowired
    private UserRepository userRepository;

    public Users registerUser(Users user){
        if (user.getPhoneNumber() == null || user.getPhoneNumber().isEmpty()){
            throw new IllegalArgumentException("Phone number cannot be empty.");
        }
        user.setCreated(LocalDateTime.ofInstant(Instant.now(), ZoneOffset.UTC));
        return userRepository.save(user);
    }
}
