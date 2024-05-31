package com.tuck.shop.add_product.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

/**
 * @author Tinashe on 6/12/2023
 */
@Getter @Setter
@Builder
public class ProductInfoDTO {

    @NotNull (message = "Field cannot be empty or null")
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
