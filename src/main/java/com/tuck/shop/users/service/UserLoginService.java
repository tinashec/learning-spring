package com.tuck.shop.users.service;

import com.google.i18n.phonenumbers.NumberParseException;
import com.google.i18n.phonenumbers.PhoneNumberUtil;
import com.google.i18n.phonenumbers.Phonenumber;
import com.tuck.shop.users.entity.Users;
import com.tuck.shop.users.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

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
        PhoneNumberUtil phoneNumberUtil = PhoneNumberUtil.getInstance();
        Phonenumber.PhoneNumber zwNumber;
        try {
            zwNumber = phoneNumberUtil.parse(phoneNumberToLogin, phoneNumberToLogin.substring(0, 4));
            if (phoneNumberUtil.isValidNumber(zwNumber)) {
                if (passwordEncoder.matches(passwordToLogin, validUser.getPassword()) && phoneNumberToLogin.equals(validUser.getPhoneNumber()))
                    isLoggedIn = true;
            } else {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid phone number");
            }
        } catch (NumberParseException numberParseException){
            throw new RuntimeException(numberParseException);
        }
        return isLoggedIn;
    }

    public Users getUserByPhone(String phoneNumber) {
        return userRepository.findByPhoneNumber(phoneNumber);
    }
}
