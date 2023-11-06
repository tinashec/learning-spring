package com.tuck.shop.users.controller;

import com.tuck.shop.users.dto.UserIdDTO;
import com.tuck.shop.users.model.LoginDetailsDto;
import com.tuck.shop.users.service.UserLoginService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * @author Tinashe on 30/8/2023
 */
@Controller
@RequestMapping ("/user")
public class LoginController {

    @Autowired
    private UserLoginService userLoginService;

    @Autowired
    private ModelMapper mapper;

    @PostMapping ("/login")
    @ResponseStatus (HttpStatus.OK)
    public @ResponseBody UserIdDTO loginUser(@RequestBody LoginDetailsDto detailsDto){
        UserIdDTO validUser;

        validUser = userLoginService.loginUser(detailsDto.getPhoneNumber(), detailsDto.getPassword());
        return validUser;
    }

}
