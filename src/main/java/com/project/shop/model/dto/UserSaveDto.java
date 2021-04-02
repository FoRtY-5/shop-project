package com.project.shop.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserSaveDto {

    private int id;

    private String firstName;

    private String lastName;

    private String email;

    private String password;

    private AddressDto address;

}
