package com.tuck.shop.users.service;

import com.tuck.shop.users.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Tinashe on 29/8/2023
 */
@Service
@Transactional
public class MyUserDetailsService {

    @Autowired
    private UserRepository userRepository;


}
