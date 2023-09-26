package com.tuck.shop.users.controller;

import com.tuck.shop.users.dto.UserCreationDTO;
import com.tuck.shop.users.dto.UserIdDTO;
import com.tuck.shop.users.service.UserRegistrationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

/**
 * @author Tinashe on 13/7/2023
 */
@RestController
@RequestMapping (path = "/user")
public class RegistrationController {
    Logger logger = LoggerFactory.getLogger(RegistrationController.class);

    @Autowired
    private UserRegistrationService userRegistrationService;

    @PostMapping (path = "/register")
    @ResponseStatus (HttpStatus.CREATED)
    public @ResponseBody UserIdDTO registerUser(@RequestBody UserCreationDTO newUser){
        logger.info("Request body: " + newUser.getPassword());
        return userRegistrationService.registerUser(newUser);
    }
}
