package com.tuck.shop.product.entity;

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
    private Integer id;

    private String name, description, category, productCode;
    private BigDecimal price;
    private boolean isRefridgerated;

    public Product(String name, BigDecimal price){
        this.name = name;
        this.price = price;
    }

    public Product(){}
}
