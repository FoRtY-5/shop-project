package com.project.shop.service;

import com.project.shop.model.dto.OrderDto;

import java.util.List;

public interface OrderService {

    List<OrderDto> getAllOrders(int page, int size);

    List<OrderDto> getOrdersByUserEmail(String email, int page, int size);

    OrderDto getOrderById(int id);

    OrderDto saveOrder(OrderDto order);

    OrderDto updateOrder(OrderDto order);

}
