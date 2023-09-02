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

        Users user = Users.builder().
                firstName("Test").
                lastName("Tester").
                phoneNumber("+26377466").
                password(passwordEncoder.encode("1111")).           // the stored password in the DB is hashed
                build();

        when(userRepository.findByPhoneNumber("+26377466")).thenReturn(user);

        Users validUser = userLoginService.getUserByPhone("+26377466");
        boolean isLoggedIn = userLoginService.loginUser(validUser, "+26377466", "1111");
        assertThat("User logged in successfully.", isLoggedIn);

        // pass in an invalid password or phone number
        isLoggedIn = userLoginService.loginUser(validUser, "+26377466", "2222");
        assertThat("User is not logged in.", !isLoggedIn);
    }
}
