package com.tuck.shop.provider;

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
public class Provider {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Integer providerId;

    private String providerName, providerAddress;
}
