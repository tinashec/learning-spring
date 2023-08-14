package com.tuck.shop.product.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.math.BigDecimal;

/**
 * @author Tinashe on 21/6/2023
 */
@Entity
public class Product {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;
    private BigDecimal price;

    public Product(String name, BigDecimal price){
        this.name = name;
        this.price = price;
    }

    public Product(){}

    public Integer getId(){
        return this.id;
    }

    public void setName(String name){
        this.name = name;
    }

    public String getName(){
        return name;
    }

    public void setPrice(String price){
        this.price = new BigDecimal(price);
    }

    public BigDecimal getPrice(){
        return price;
    }
}
