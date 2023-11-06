package com.tuck.shop.inventory;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

/**
 * @author Tinashe on 3/11/2023
 */
@Entity
@Getter @Setter
public class Inventory {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Integer id;

    private Integer quantityAvailable, minStockLevel, reorderPoint;
}
