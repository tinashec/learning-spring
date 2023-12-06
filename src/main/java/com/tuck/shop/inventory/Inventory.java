package com.tuck.shop.inventory;

import com.tuck.shop.add_product.entity.Product;
import jakarta.persistence.*;
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

    @ManyToOne
    @JoinColumn (name = "productId")
    private Product product;
}
