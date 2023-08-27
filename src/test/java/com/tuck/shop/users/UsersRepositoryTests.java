package com.tuck.shop.users;

import com.tuck.shop.users.entity.Users;
import com.tuck.shop.users.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

/**
 * @author Tinashe on 22/7/2023
 */

@DataJpaTest
public class UsersRepositoryTests {

    @Autowired
    TestEntityManager entityManager;

    @Autowired
    UserRepository userRepository;

    @Test
    public void saveUserTest(){

        // given, arrange the data
        Users testUser = Users.builder().
                firstName("Test").
                password("1234").
                phoneNumber("123456").
                build();

        // when, act on the data
        entityManager.persistAndFlush(testUser);
        Users savedUser = userRepository.findByPhoneNumber(testUser.getPhoneNumber());

        // then, assert the result of the action
        assert(savedUser.getFirstName().equals(testUser.getFirstName()));
        assert(savedUser.getPhoneNumber().equals(testUser.getPhoneNumber()));
    }
}
