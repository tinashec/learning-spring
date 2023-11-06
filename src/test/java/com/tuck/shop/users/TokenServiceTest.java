package com.tuck.shop.users;

import com.tuck.shop.service.TokenService;
import com.tuck.shop.users.dto.UserIdDTO;
import com.tuck.shop.users.entity.Users;
import com.tuck.shop.users.model.LoginDetailsDto;
import com.tuck.shop.users.service.UserLoginService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.hamcrest.MatcherAssert.assertThat;

/**
 * @author Tinashe on 6/11/2023
 */

@SpringBootTest
public class TokenServiceTest {

    private LoginDetailsDto userTologin = new LoginDetailsDto();
    private Users user = new Users();

    @Autowired
    private UserLoginService userLoginService;

    @Autowired
    private TokenService tokenService;

    @Test
    public void generateTokenOnLoginTest(){

        // given, a valid registered user
        userTologin.setPhoneNumber("+263774666249");
        userTologin.setPassword("1234");

        user.setFirstName("Test");
        user.setPassword("1234");
        user.setPhoneNumber("+264774666249");

        // when generate token
        String token = tokenService.generateToken(user);
        // then token is generated
        assertThat("Token is generated", token.startsWith("ey"));

        // when they login
        UserIdDTO loggedInUser = userLoginService.loginUser(userTologin.getPhoneNumber(), userTologin.getPassword());
        // then a token should be provided
        assertThat("Token generated is valid.", loggedInUser.getToken() != null);
    }
}
