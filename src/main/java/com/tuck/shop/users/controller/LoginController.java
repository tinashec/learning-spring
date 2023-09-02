package com.tuck.shop.users.controller;

import com.tuck.shop.users.entity.Users;
import com.tuck.shop.users.model.LoginDetailsDto;
import com.tuck.shop.users.service.UserLoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

/**
 * @author Tinashe on 30/8/2023
 */
@Controller
@RequestMapping ("/user")
public class LoginController {

    @Autowired
    private UserLoginService userLoginService;

    @PostMapping ("/login")
    @ResponseStatus (HttpStatus.OK)
    public @ResponseBody Users loginUser(@RequestBody LoginDetailsDto detailsDto){
        Users validUser = userLoginService.getUserByPhone(detailsDto.getPhoneNumber());
        boolean isLoggedIn = userLoginService.loginUser(validUser, detailsDto.getPhoneNumber(), detailsDto.getPassword());
        if (isLoggedIn){
            return validUser;
        } else {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Invalid credentials");
        }
    }

}
