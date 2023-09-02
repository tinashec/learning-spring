package com.tuck.shop.users.service;

import com.tuck.shop.users.entity.Users;
import com.tuck.shop.users.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * @author Tinashe on 30/8/2023
 */
@Service
public class UserLoginService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    public boolean loginUser(Users validUser, String phoneNumberToLogin, String passwordToLogin){
        boolean isLoggedIn = false;
        if (getUserByPhone(phoneNumberToLogin) == null){
            return false;
        }
        if (passwordEncoder.matches(passwordToLogin, validUser.getPassword()) && phoneNumberToLogin.equals(validUser.getPhoneNumber()))
            isLoggedIn = true;
        return isLoggedIn;
    }

    public Users getUserByPhone(String phoneNumber) {
        return userRepository.findByPhoneNumber(phoneNumber);
    }
}
