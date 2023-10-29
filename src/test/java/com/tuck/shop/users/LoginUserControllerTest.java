package com.tuck.shop.users;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tuck.shop.users.dto.UserIdDTO;
import com.tuck.shop.users.model.LoginDetailsDto;
import com.tuck.shop.users.repository.UserRepository;
import com.tuck.shop.users.service.UserLoginService;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.hamcrest.Matchers.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * @author Tinashe on 30/8/2023
 */
@SpringBootTest
@AutoConfigureMockMvc
public class LoginUserControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    @Autowired
    UserRepository userRepository;

    @MockBean
    UserLoginService loginService;

    @Autowired
    ModelMapper mapper;

    @Test
    public void loginControllerTest() throws Exception {
        // given a user, when they log in, then an auth token is returned
        LoginDetailsDto detailsDto = new LoginDetailsDto();
        detailsDto.setPhoneNumber("+263774666249");
        detailsDto.setPassword("1234");

        UserIdDTO loggedInUser = new UserIdDTO();
        loggedInUser.setFirstName("Tinashe");

        // when, mock the service
        when(loginService.loginUser(any(String.class), any(String.class))).thenReturn(loggedInUser);

        mockMvc.perform(MockMvcRequestBuilders.post("/user/login").
                    contentType(MediaType.APPLICATION_JSON).
                    content(objectMapper.writeValueAsString(detailsDto))).
                andExpect(status().isOk()).
                andExpect(jsonPath("$.firstName", is(equalTo("Tinashe"))));
    }
}
