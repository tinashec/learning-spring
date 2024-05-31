package com.tuck.shop.add_product.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NonNull;
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

    @Column (unique = true)
    private String productCode;

    private String name, description, category;
    @NonNull
    private BigDecimal sellingPrice, buyingPrice;
    private boolean isRefridgerated;

    private int quantity;

    public Product(String name, BigDecimal sellingPrice){
        this.name = name;
        this.sellingPrice = sellingPrice;
    }

    public Product(){}
}
