package com.learning.spring;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * @author Tinashe on 25/6/2023
 */
@SpringBootTest
@AutoConfigureMockMvc
public class ProductControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void getProductTest() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/product").accept(MediaType.APPLICATION_JSON)).
                andExpect(status().isOk()).
                andExpect(jsonPath("$.productName", is("Stock")));
    }

    @Test
    public void getProductByNameTest() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/product?name=Cerevita")).
                andExpect(status().isOk()).
                andExpect(jsonPath("productPrice", is(equalTo(12)))).
                andExpect(jsonPath("productName", is(equalTo("Cerevita"))));
    }
}
