package com.project.shop.model.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddressDto {

    private int id;

    private String street;

    private String city;

    private String country;

    private String postalCode;


}
