package com.tuck.shop;

import com.tuck.shop.service.TokenService;
import com.tuck.shop.users.dto.UserIdDTO;
import com.tuck.shop.users.service.UserLoginService;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * @author Tinashe on 25/6/2023
 */
@SpringBootTest
@AutoConfigureMockMvc
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class ProductControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private TokenService tokenService;

    @Autowired
    private UserLoginService loginService;

    @Autowired
    private ModelMapper modelMapper;

    private UserIdDTO loggedInUser;
    private String accessToken;

    @BeforeAll
    public void loginUser(){
        loggedInUser = loginService.loginUser("+263774666249", "1234");
        accessToken = loggedInUser.getToken();
    }

    @Test
    public void getSampleProductTest() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/product/sample").
                header("Authorization", "Bearer " + accessToken).
                accept(MediaType.APPLICATION_JSON)).
                andExpect(status().isOk()).
                andExpect(jsonPath("$.name", is("Stock")));
    }

    @Test
    public void getSampleProductByNameTest() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/product/sample?name=Cerevita").
                        header("Authorization", "Bearer " + accessToken)).
                andExpect(status().isOk()).
                andExpect(jsonPath("price", is(equalTo(155)))).
                andExpect(jsonPath("name", is(equalTo("Cerevita"))));
    }

    @Test
    public void addProductTest() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/product/add").
                        header("Authorization", "Bearer " + accessToken).
                        queryParam("name", "Flakes").
                        queryParam("price", "13")).
                andExpect(status().isCreated()).
                andExpect(jsonPath("$.name", is(equalTo("Flakes")))).
                andExpect(jsonPath("$.id", is(notNullValue())));
    }

    @Test
    public void getProductByIdTest() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/product/3").
                        header("Authorization", "Bearer " + accessToken)).
                andExpect(status().isOk());
    }
}
