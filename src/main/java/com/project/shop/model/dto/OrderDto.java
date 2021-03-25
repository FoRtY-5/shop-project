package com.project.shop.model.dto;

import com.project.shop.model.enums.Status;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderDto {

    private int id;

    private String shipmentAddress;

    private double price;

    private Status status;

    private UserDto user;

}
