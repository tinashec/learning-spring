package com.tuck.shop.users;

import com.tuck.shop.users.entity.User;
import com.tuck.shop.users.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @author Tinashe on 22/7/2023
 */
@SpringBootTest
public class UserRepositoryTests {

    @Autowired
    TestEntityManager entityManager;

    @Autowired
    UserRepository userRepository;

    @Test
    public void saveUserTest(){
        User testUser = User.builder().
                firstName("Test")
                .build();
        testUser = entityManager.persistAndFlush(testUser);
    }
}
