package com.tuck.shop.users;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tuck.shop.users.controller.RegistrationController;
import com.tuck.shop.users.entity.Users;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * @author Tinashe on 16/7/2023
 */
@AutoConfigureMockMvc
@SpringBootTest
public class UsersRegistrationControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private RegistrationController registrationController;

    @Test
    public void registerUserTest() throws Exception {
        Users testUser = Users.builder().
                firstName("New").
                lastName("NoDB").
                phoneNumber("999912").
                build();

        when(registrationController.registerUser(testUser)).thenReturn(testUser);
        // ToDo: mock such that it returns a JsonObject as part of the body response

        mockMvc.perform(MockMvcRequestBuilders.post("/user/register").
                    contentType(MediaType.APPLICATION_JSON).
                    content(new ObjectMapper().writeValueAsString(testUser))).
                andExpect(status().isCreated());
    }
}

