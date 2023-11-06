package com.tuck.shop.location;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

/**
 * @author Tinashe on 4/11/2023
 */

@Entity
@Getter @Setter
public class Location {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Integer locationId;

    private String locationName, locationAddress;
}
