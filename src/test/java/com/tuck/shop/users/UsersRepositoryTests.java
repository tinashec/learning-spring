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

        Users testUser = Users.builder().
                firstName("Test").
                build();
        entityManager.persistAndFlush(testUser);
        Users savedUser = userRepository.findByFirstName(testUser.getFirstName());
        assert(savedUser.getFirstName().equals(testUser.getFirstName()));
    }
}
