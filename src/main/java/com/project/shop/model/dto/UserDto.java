package com.project.shop.model.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {

    private int id;

    private boolean isActive;

    private String firstName;

    private String lastName;

    private String email;

    private String password;

    private AddressDto address;

    private String newsletter;

    private Set<RoleDto> role = new HashSet<>();

    private Set<ProductDto> product = new HashSet<>();

}
