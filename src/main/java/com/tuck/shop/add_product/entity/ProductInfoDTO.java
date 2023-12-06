package com.tuck.shop.add_product.entity;

import lombok.Getter;
import lombok.Setter;

/**
 * @author Tinashe on 6/12/2023
 */
@Getter @Setter
public class ProductInfoDTO {

    private String productName, productDescription, productCategory, productCode;
    private Integer buyingPrice, sellingPrice;
}
