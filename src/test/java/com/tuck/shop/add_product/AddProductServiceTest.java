package com.tuck.shop.add_product;

import com.tuck.shop.add_product.entity.Product;
import com.tuck.shop.add_product.entity.ProductInfoDTO;
import com.tuck.shop.add_product.service.AddProductService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.hamcrest.MatcherAssert.assertThat;

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
                    productCode("   ").
                    productCategory("Cereal").
                    productName("Cerevita").
                    productDescription("Nestle Cerevita 500g").
                    buyingPrice(2.70).
                    sellingPrice(3.5).
                    quantity(10).
                build();
        assertThat("Product does not exist:", addProductService.getProduct(newProduct.getProductCode()) == null);
        Product addedProduct = addProductService.addProduct(newProduct);
        assertThat("Product has been added", addedProduct.getProductCode().equals(newProduct.getProductCode()));

    }
}
