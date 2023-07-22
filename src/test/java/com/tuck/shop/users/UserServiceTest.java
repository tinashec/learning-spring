package com.tuck.shop.users;

import com.tuck.shop.users.entity.User;
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
public class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserRegistrationService userRegistrationService;

    @Test
    public void useRegistrationReturnsUserTest(){
        when(userRepository.save(any(User.class))).thenReturn(new User());

        User user = User.builder().
                firstName("Test").
                lastName("User").
                build();
        User registeredUser = userRegistrationService.registerUser(user);
        assertThat(registeredUser.getFirstName(), is(equalTo(user.getFirstName())));
    }
}
