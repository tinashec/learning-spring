package com.tuck.shop;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tuck.shop.users.entity.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * @author Tinashe on 16/7/2023
 */
@AutoConfigureMockMvc
@SpringBootTest
public class UserRegistrationControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void registerUserTest() throws Exception {
        User testUser = User.builder().
                firstName("Tinashe").
                lastName("Chinyanga").
                phoneNumber("+263774666249").
                build();

        mockMvc.perform(MockMvcRequestBuilders.post("/user/register").
                contentType(MediaType.APPLICATION_JSON).
                content(new ObjectMapper().writeValueAsString(testUser))).
                andExpect(status().isCreated()).
                andExpect(jsonPath("$.phoneNumber", is(equalTo("+263774666249"))));
    }
}
