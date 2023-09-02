package com.tuck.shop.users.model;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

/**
 * @author Tinashe on 30/8/2023
 */
@Getter @Setter
@Data
public class LoginDetailsDto {
    private String phoneNumber;
    private String password;

}
