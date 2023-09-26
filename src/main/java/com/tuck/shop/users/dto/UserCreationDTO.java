package com.tuck.shop.users.dto;

import lombok.Getter;
import lombok.Setter;

/**
 * @author Tinashe on 25/9/2023
 */
@Getter @Setter
public class UserCreationDTO {

    private String firstName, lastName, password, phoneNumber;
}
