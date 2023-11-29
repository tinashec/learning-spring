package com.tuck.shop.add_store;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tuck.shop.service.TokenService;
import com.tuck.shop.store.dto.StoreDTO;
import com.tuck.shop.users.dto.UserIdDTO;
import com.tuck.shop.users.entity.Users;
import com.tuck.shop.users.service.UserLoginService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.core.userdetails.User;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * @author Tinashe on 20/11/2023
 */

@SpringBootTest
@AutoConfigureMockMvc
public class AddStoreControllerTest {
    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    @Autowired
    TokenService tokenService;

    @Autowired
    UserLoginService loginService;

    @Test
    public void addStoreTest() throws Exception {
        UserIdDTO existingUser = loginService.loginUser("+263774666249", "1234");
        String token = existingUser.getToken();

        StoreDTO myStore = StoreDTO.builder().
                    storeName("Sibale groceries").
                build();
        mockMvc.perform(MockMvcRequestBuilders.post("/store/add").
                    header("Authorization", "Bearer " + token).
                    contentType(MediaType.APPLICATION_JSON).
                    content(objectMapper.writeValueAsString(myStore))).
                andExpect(status().isCreated());
    }
}
