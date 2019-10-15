package com.sandi.authorizationsvc.model;

import lombok.Getter;
import lombok.Setter;

import java.time.Instant;
import java.util.Set;

@Setter
@Getter
public class Customer {

    private Integer id;
    private String firstName;
    private String lastName;
    private String customerName;
    private String password;
    private String phoneNumber;
    private String email;
    private String country;
    private Instant createdOn;
    private Instant updatedOn;
    private Instant lastLogin;
    private Set<Role> roles;

}
