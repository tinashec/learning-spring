package com.tuck.shop.add_product;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tuck.shop.add_product.entity.Product;
import com.tuck.shop.add_product.entity.ProductInfoDTO;
import com.tuck.shop.add_product.service.AddProductService;
import com.tuck.shop.users.dto.UserIdDTO;
import com.tuck.shop.users.service.UserLoginService;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.hamcrest.Matchers.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.isNotNull;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * @author Tinashe on 6/12/2023
 */

@SpringBootTest
@AutoConfigureMockMvc
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class AddProductControllerTest {

    @Autowired
    private UserLoginService userLoginService;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private AddProductService addProductService;

    private UserIdDTO loggedUser;

    @Autowired
    MockMvc mockMvc;

    @BeforeAll
    public void loginUser(){
        // login a user, you'll need the token to access the resource
        loggedUser = userLoginService.loginUser("+263774666249", "1234");
    }

    @Test
    public void addProductControllerTest() throws Exception {
        // given (a logged in user)
        Product newProduct = new Product();
        newProduct.setProductId(1);
        // when they visit the add product endpoint
        when(addProductService.addProduct(any(ProductInfoDTO.class))).thenReturn(newProduct);
        mockMvc.perform(MockMvcRequestBuilders.post("/product/add").
                    contentType(MediaType.APPLICATION_JSON).
                    header("Authorization", "Bearer " + loggedUser.getToken()).
                    content(objectMapper.writeValueAsString(new ProductInfoDTO()))).
                andExpect(status().isCreated()).
                andExpect(jsonPath("$.productId", is(1)));
        // then they can add the product
    }
}
