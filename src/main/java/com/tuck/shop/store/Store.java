package com.tuck.shop.store;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

/**
 * @author Tinashe on 16/11/2023
 */
@Entity
@Getter @Setter
public class Store {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id;

    private String storeName, storeLocation, storeAddress, storeDescription;

    public Store(){}
}
