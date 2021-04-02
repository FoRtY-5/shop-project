package com.project.shop.service;

import com.project.shop.model.dto.OrderDto;

import java.util.List;

public interface OrderService {

    List<OrderDto> getAllOrders(int page, int size);

    OrderDto getOrderById(int id);

}
