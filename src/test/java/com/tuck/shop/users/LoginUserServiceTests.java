package com.tuck.shop.users;

import com.tuck.shop.users.entity.Users;
import com.tuck.shop.users.repository.UserRepository;
import com.tuck.shop.users.service.UserLoginService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Mockito.when;

/**
 * @author Tinashe on 2/9/2023
 */
@SpringBootTest
public class LoginUserServiceTests {

    @MockBean
    UserRepository userRepository;

    @Autowired
    UserLoginService userLoginService;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Test
    public void loginUserTest(){

        String number = "+263774666249";

        Users user = Users.builder().
                firstName("Test").
                lastName("Tester").
                phoneNumber(number).
                password(passwordEncoder.encode("1111")).           // the stored password in the DB is hashed
                build();

        // mock the repository, otherwise you'll need to know the user stored in the DB and their password
        when(userRepository.findByPhoneNumber(number)).thenReturn(user);

        Users validUser = userLoginService.getUserByPhone(number);
        boolean isLoggedIn = userLoginService.loginUser(validUser, number, "1111");
        assertThat("User logged in successfully.", isLoggedIn);

        // pass in an invalid password or phone number
        isLoggedIn = userLoginService.loginUser(validUser, number, "2222");
        assertThat("User is not logged in.", !isLoggedIn);
    }
}
