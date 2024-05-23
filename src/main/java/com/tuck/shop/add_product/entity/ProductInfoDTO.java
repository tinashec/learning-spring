package com.tuck.shop.add_product.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

/**
 * @author Tinashe on 6/12/2023
 */
@Getter @Setter
@Builder
public class ProductInfoDTO {

    private String productName, productDescription, productCategory, productCode;
    private Double buyingPrice, sellingPrice;
    private Integer quantity;

    public ProductInfoDTO(){}

    public ProductInfoDTO(String productName, String productDescription, String productCategory, String productCode,
                          Double buyingPrice, Double sellingPrice, Integer quantity){
        this.productName = productName;
        this.productDescription = productDescription;
        this.productCategory = productCategory;
        this.productCode = productCode;
        this.buyingPrice = buyingPrice;
        this.sellingPrice = sellingPrice;
        this.quantity = quantity;
    }
}
