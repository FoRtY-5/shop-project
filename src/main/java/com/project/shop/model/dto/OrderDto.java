package com.project.shop.model.dto;

import com.project.shop.model.enums.Status;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderDto {

    private int id;

    private AddressDto shipmentAddress;

    private BigDecimal price;

    private Status status;

    private UserDto user;

}
