package com.tuck.shop.users.service;

import com.google.i18n.phonenumbers.NumberParseException;
import com.google.i18n.phonenumbers.PhoneNumberUtil;
import com.google.i18n.phonenumbers.Phonenumber;
import com.tuck.shop.service.TokenService;
import com.tuck.shop.users.dto.UserCreationDTO;
import com.tuck.shop.users.dto.UserIdDTO;
import com.tuck.shop.users.entity.Users;
import com.tuck.shop.users.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

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

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private TokenService tokenService;

    private ModelMapper modelMapper = new ModelMapper();

    public UserIdDTO registerUser(UserCreationDTO user){
        Users createUser = modelMapper.map(user, Users.class);
        UserIdDTO createdUser;
        if (user.getPhoneNumber() == null || user.getPhoneNumber().isEmpty() || user.getPassword() == null){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Phone number/password cannot be empty.");
        }
        if (isPhoneExists(user.getPhoneNumber())){
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Phone number already exists");
        }
        PhoneNumberUtil phoneNumberUtil = PhoneNumberUtil.getInstance();
        Phonenumber.PhoneNumber zwNumber;
        try {
            zwNumber = phoneNumberUtil.parse(user.getPhoneNumber(), user.getPhoneNumber().substring(0, 4));
            if (phoneNumberUtil.isValidNumber(zwNumber)){
                // create the user
                createUser.setPassword(passwordEncoder.encode(user.getPassword()));
                createUser.setCreated(LocalDateTime.ofInstant(Instant.now(), ZoneOffset.UTC));
                createdUser = modelMapper.map(userRepository.save(createUser), UserIdDTO.class);
                // user created, authenticate and return the user?
                if (createdUser != null)
                    createdUser.setToken(tokenService.generateToken(createUser));
                return createdUser;
            }
        } catch (NumberParseException e) {
            throw new RuntimeException(e);
        }
        throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid phone number, please check the number.");
    }

    /**
     * check if the phone number already exists in the DB
     * @param phoneNumber number to be checked in the DB
     * @return true or false depending on whether the number exists
     */
    public boolean isPhoneExists(String phoneNumber) {
        return userRepository.findByPhoneNumber(phoneNumber) != null;
    }
}
