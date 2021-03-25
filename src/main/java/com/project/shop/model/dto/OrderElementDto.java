package com.project.shop.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderElementDto {

    private int id;

    private int quantity;

    private OrderDto order;

    private ProductDto product;

}
