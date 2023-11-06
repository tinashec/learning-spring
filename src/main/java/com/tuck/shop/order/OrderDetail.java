package com.tuck.shop.order;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

/**
 * @author Tinashe on 3/11/2023
 */

@Entity
@Getter @Setter
public class OrderDetail {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Integer orderDetailId;

    private Integer orderQuantity;

    private LocalDateTime expectedDeliveryDate, actualDeliveryDate;
}
