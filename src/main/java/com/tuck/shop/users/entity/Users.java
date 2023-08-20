package com.tuck.shop.users.entity;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.tuck.shop.utils.CustomDateSerieliser;
import jakarta.annotation.Nonnull;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalDateTime;

/**
 * @author Tinashe on 12/7/2023
 */
@Getter @Setter @Builder
@Entity
public class Users {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Integer id;

    private String firstName, lastName, password;

    @Nonnull @Column(unique = true)
    private String phoneNumber;

    @Column (updatable = false)
    @JsonSerialize (using = CustomDateSerieliser.class)
    @CreatedDate
    private LocalDateTime created;

    @JsonSerialize (using = CustomDateSerieliser.class)
    @LastModifiedDate
    private LocalDateTime modifiedDate;

    public Users(){}

    public Users(Integer id, String name, String lastName, String password, String phoneNumber, LocalDateTime createdDate,
                 LocalDateTime modified){
        this.id = id;
        this.firstName = name;
        this.lastName = lastName;
        this.password = password;
        this.phoneNumber = phoneNumber;
        this.created = createdDate;
        this.modifiedDate = modified;
    }
}
