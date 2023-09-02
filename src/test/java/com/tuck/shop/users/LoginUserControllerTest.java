package com.tuck.shop.users;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tuck.shop.users.entity.Users;
import com.tuck.shop.users.model.LoginDetailsDto;
import com.tuck.shop.users.repository.UserRepository;
import com.tuck.shop.users.service.UserLoginService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
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

    @Test
    public void loginControllerTest() throws Exception {
        // given a user, when they log in, then an auth token is returned
        LoginDetailsDto detailsDto = new LoginDetailsDto();
        detailsDto.setPhoneNumber("+263774666249");
        detailsDto.setPassword("1234");

        // when, mock the service
        when(loginService.getUserByPhone(any(String.class))).thenReturn(userRepository.findByPhoneNumber(detailsDto.getPhoneNumber()));
        when(loginService.loginUser(any(Users.class), any(String.class), any(String.class))).thenReturn(true);

        mockMvc.perform(MockMvcRequestBuilders.post("/user/login").
                    contentType(MediaType.APPLICATION_JSON).
                    content(objectMapper.writeValueAsString(detailsDto))).
                andExpect(status().isOk()).
                andExpect(jsonPath("$.firstName", is(equalTo("Tinashe"))));
    }
}
