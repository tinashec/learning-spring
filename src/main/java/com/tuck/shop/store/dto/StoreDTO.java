package com.tuck.shop.store.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

/**
 * @author Tinashe on 16/11/2023
 */
@Getter @Setter
@Builder
public class StoreDTO {

    private String storeName, storeAddress, storeDescription;

    public StoreDTO(){}

    public StoreDTO(String name, String address, String description){
        this.storeName = name;
        this.storeAddress = address;
        this.storeDescription = description;
    }
}
