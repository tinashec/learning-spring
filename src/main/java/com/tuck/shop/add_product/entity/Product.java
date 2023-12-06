package com.tuck.shop.add_product.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

/**
 * @author Tinashe on 21/6/2023
 */
@Entity
@Getter @Setter
public class Product {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Integer productId;

    private String name, description, category, productCode;
    private BigDecimal sellingPrice, buyingPrice;
    private boolean isRefridgerated;

    public Product(String name, BigDecimal sellingPrice){
        this.name = name;
        this.sellingPrice = sellingPrice;
    }

    public Product(){}
}
