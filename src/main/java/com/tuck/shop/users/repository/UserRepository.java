package com.tuck.shop.users.repository;

import com.tuck.shop.users.entity.User;
import org.springframework.data.repository.CrudRepository;

/**
 * @author Tinashe on 12/7/2023
 */
public interface UserRepository extends CrudRepository<User, Integer> {
}
