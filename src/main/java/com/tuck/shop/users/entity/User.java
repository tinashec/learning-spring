package com.tuck.shop.users.entity;

import jakarta.annotation.Nonnull;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

/**
 * @author Tinashe on 12/7/2023
 */
@Getter @Setter @Builder
@Entity
public class User {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Integer id;

    private String firstName, lastName, password;

    @Nonnull @Column(unique = true)
    private String phoneNumber;

    public User(){}

    public User(Integer id, String name, String lastName, String password, String phoneNumber){
        this.id = id;
        this.firstName = name;
        this.lastName = lastName;
        this.password = password;
        this.phoneNumber = phoneNumber;
    }
}
