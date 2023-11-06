package com.tuck.shop.users.service;

import com.google.i18n.phonenumbers.NumberParseException;
import com.google.i18n.phonenumbers.PhoneNumberUtil;
import com.google.i18n.phonenumbers.Phonenumber;
import com.tuck.shop.service.TokenService;
import com.tuck.shop.users.dto.UserIdDTO;
import com.tuck.shop.users.entity.Users;
import com.tuck.shop.users.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

    private static final Logger LOG = LoggerFactory.getLogger(UserLoginService.class);

    @Autowired
    UserRepository userRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    private TokenService tokenService;

    @Autowired
    private ModelMapper mapper;

    /**
     * login a user based on the phone number and password. Once authenticated, the response contains user details and
     * a token used to grant access to authenticated resources
     * @param phoneNumberToLogin phone number of registered user
     * @param passwordToLogin password of the registered user
     * @return @{@link UserIdDTO} details of the logged-in user
     */
    public UserIdDTO loginUser(String phoneNumberToLogin, String passwordToLogin){
        String jwtToken;
        UserIdDTO loggedInUser;
        Users validUser = getUserByPhone(phoneNumberToLogin);
        if (validUser == null){
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Invalid credentials");
        }
        PhoneNumberUtil phoneNumberUtil = PhoneNumberUtil.getInstance();
        Phonenumber.PhoneNumber zwNumber;
        try {
            zwNumber = phoneNumberUtil.parse(phoneNumberToLogin, phoneNumberToLogin.substring(0, 4));
            if (phoneNumberUtil.isValidNumber(zwNumber)) {
                if (passwordEncoder.matches(passwordToLogin, validUser.getPassword()) && phoneNumberToLogin.equals(validUser.getPhoneNumber())) {
                    // ToDo: generate JWT token and return as part of the response
                    jwtToken = tokenService.generateToken(validUser);
                    LOG.info("Token generated: {}", jwtToken);
                    loggedInUser = mapper.map(validUser, UserIdDTO.class);
                    loggedInUser.setToken(jwtToken);
                }else {
                    throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid credentials");
                }
            } else {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid phone number");
            }
        } catch (NumberParseException numberParseException){
            throw new RuntimeException(numberParseException);
        }
        return loggedInUser;
    }

    public Users getUserByPhone(String phoneNumber) {
        return userRepository.findByPhoneNumber(phoneNumber);
    }
}
