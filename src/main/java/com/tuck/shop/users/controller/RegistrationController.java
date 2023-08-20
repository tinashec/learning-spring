package com.tuck.shop.users.controller;

import com.tuck.shop.users.entity.Users;
import com.tuck.shop.users.service.UserRegistrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

/**
 * @author Tinashe on 13/7/2023
 */
@RestController
@RequestMapping (path = "/user")
public class RegistrationController {

    @Autowired
    private UserRegistrationService userRegistrationService;

    @PostMapping (path = "/register")
    @ResponseStatus (HttpStatus.CREATED)
    public @ResponseBody Users registerUser(@RequestBody Users newUser){
        return userRegistrationService.registerUser(newUser);
    }
}
