package com.sandi.authorizationsvc.model;

import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
public class Role {

    private Integer roleId;
    private String name;
    private Set<Right> rights;

}
