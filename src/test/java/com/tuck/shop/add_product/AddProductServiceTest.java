package com.tuck.shop.add_product;

import com.tuck.shop.add_product.entity.ProductInfoDTO;
import com.tuck.shop.add_product.service.AddProductService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @author Tinashe on 29/11/2023
 */

@SpringBootTest
public class AddProductServiceTest {

    @Autowired
    AddProductService addProductService;

    @Test
    public void addProductTest(){
        /**
         * check if the product exists, if not add the product
         * add the product details (name, description etc.)
         * add the product inventory details
         * update entities (inventory and product)
         * persist the product (mock this)?
         */
        ProductInfoDTO newProduct = ProductInfoDTO.builder().
                    productCode("CER1234").
                    productCategory("Cereal").
                    productName("Cerevita").
                    productDescription("Nestle Cerevita 500g").
                    buyingPrice(2.70).
                    sellingPrice(3.5).
                build();

        addProductService.addProduct(newProduct);
    }
}
