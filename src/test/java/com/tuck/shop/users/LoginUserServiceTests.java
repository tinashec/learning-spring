package com.tuck.shop.users;

import com.tuck.shop.users.dto.UserIdDTO;
import com.tuck.shop.users.entity.Users;
import com.tuck.shop.users.repository.UserRepository;
import com.tuck.shop.users.service.UserLoginService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.server.ResponseStatusException;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Mockito.when;

/**
 * @author Tinashe on 2/9/2023
 */
@SpringBootTest
public class LoginUserServiceTests {

    public static final String INVALID_PASSWORD_TO_LOGIN = "2222";
    public static final String VALID_PASSWORD_TO_LOGIN = "1111";
    @MockBean
    UserRepository userRepository;

    @Autowired
    UserLoginService userLoginService;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Test
    public void loginUserTest(){

        String userPhoneNumber = "+263774666249";

        Users user = Users.builder().
                firstName("Test").
                lastName("Tester").
                phoneNumber(userPhoneNumber).
                password(passwordEncoder.encode(VALID_PASSWORD_TO_LOGIN)).           // the stored password in the DB is hashed
                build();

        // mock the repository, otherwise you'll need to know the user stored in the DB and their password
        when(userRepository.findByPhoneNumber(userPhoneNumber)).thenReturn(user);

        UserIdDTO loggedInUser = userLoginService.loginUser(userPhoneNumber, VALID_PASSWORD_TO_LOGIN);
        assertThat("User logged in successfully.", loggedInUser.getFirstName().equals(user.getFirstName()));
        assertThat("Token is generated", loggedInUser.getToken() != null);

        // pass in an invalid password or phone number
        try {
            userLoginService.loginUser(userPhoneNumber, INVALID_PASSWORD_TO_LOGIN);
        } catch (ResponseStatusException exception){
            assertThat("User is not logged in, bad request.", exception.getStatusCode().is4xxClientError());
            assertThat("Invalid credentials message is returned.", exception.getReason().equals("Invalid credentials"));
        }
    }
}
