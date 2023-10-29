package com.tuck.shop.users.dto;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.tuck.shop.utils.CustomDateSerieliser;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

/**
 * @author Tinashe on 25/9/2023
 * returns the user details
 */
@Getter @Setter
public class UserIdDTO {
    private Integer id;
    private String firstName, lastName, phoneNumber, token;

    @JsonSerialize (using = CustomDateSerieliser.class)
    private LocalDateTime created, modified;
}
