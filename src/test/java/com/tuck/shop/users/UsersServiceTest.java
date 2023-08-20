package com.tuck.shop.users;

import com.tuck.shop.users.entity.Users;
import com.tuck.shop.users.repository.UserRepository;
import com.tuck.shop.users.service.UserRegistrationService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

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

    @InjectMocks
    private UserRegistrationService userRegistrationService;

    @Test
    public void useRegistrationReturnsUserTest(){

        Users user = Users.builder().
                firstName("Test").
                lastName("Users").
                phoneNumber("123456").
                build();

        when(userRepository.save(any(Users.class))).thenReturn(user);

        Users registeredUser = userRegistrationService.registerUser(user);
        assertThat(registeredUser.getFirstName(), is(equalTo(user.getFirstName())));
    }
}
