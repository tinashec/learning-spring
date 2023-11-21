package com.tuck.shop.add_store;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tuck.shop.store.dto.StoreDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
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

    @Test
    public void addStoreTest() throws Exception {
        StoreDTO myStore = StoreDTO.builder().
                    storeName("Sibale groceries").
                build();
        mockMvc.perform(MockMvcRequestBuilders.post("/store/add").
                    contentType(MediaType.APPLICATION_JSON).
                    content(objectMapper.writeValueAsString(myStore))).
                andExpect(status().isCreated());
    }
}
