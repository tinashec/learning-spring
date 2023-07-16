package com.tuck.shop;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.hamcrest.Matchers.*;
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
    public void getSampleProductTest() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/product/sample").accept(MediaType.APPLICATION_JSON)).
                andExpect(status().isOk()).
                andExpect(jsonPath("$.name", is("Stock")));
    }

    @Test
    public void getSampleProductByNameTest() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/product/sample?name=Cerevita")).
                andExpect(status().isOk()).
                andExpect(jsonPath("price", is(equalTo(155)))).
                andExpect(jsonPath("name", is(equalTo("Cerevita"))));
    }

    @Test
    public void addProductTest() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/product/add").
                        queryParam("name", "Flakes").
                        queryParam("price", "13")).
                andExpect(status().isCreated()).
                andExpect(jsonPath("$.name", is(equalTo("Flakes")))).
                andExpect(jsonPath("$.id", is(notNullValue())));
    }

    @Test
    public void getProductByIdTest() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/product/3")).
                andExpect(status().isOk());
    }
}
