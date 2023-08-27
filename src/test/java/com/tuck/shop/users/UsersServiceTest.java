package com.tuck.shop.users;

import com.tuck.shop.users.entity.Users;
import com.tuck.shop.users.repository.UserRepository;
import com.tuck.shop.users.service.UserRegistrationService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

/**
 * @author Tinashe on 22/7/2023
 */
@SpringBootTest
public class UsersServiceTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @InjectMocks
    private UserRegistrationService userRegistrationService;

    // given, arrange the data
    Users user = Users.builder().
            firstName("Test").
            lastName("Users").
            phoneNumber("+263770123456").
            build();

    @Test
    public void userRegistrationReturnsUserTest(){

        // given, arrange the data
        Users user = Users.builder().
                firstName("Test").
                lastName("Users").
                phoneNumber("+263770123456").
                password("1234").
                build();

        // when, act on the data
        when(userRepository.save(any(Users.class))).thenReturn(user);
        when(passwordEncoder.encode(user.getPassword())).thenReturn(user.getPassword());

        // then, assert the outcome of the action
        Users registeredUser = userRegistrationService.registerUser(user);
        assertThat(registeredUser.getFirstName(), is(equalTo(user.getFirstName())));
    }

    @Test
    public void phoneAlreadyExistsTest(){
        // mock find user phone number to return null
        when(userRepository.findByPhoneNumber(user.getPhoneNumber())).thenReturn(user);

        boolean isExistingNumber = userRegistrationService.isPhoneExists(user.getPhoneNumber());

        // then
        assertThat(isExistingNumber, is(true));
    }
}
