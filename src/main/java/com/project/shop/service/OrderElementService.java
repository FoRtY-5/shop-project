package com.project.shop.service;

import com.project.shop.model.dto.OrderElementDto;

import java.util.List;

public interface OrderElementService {

    List<OrderElementDto> getOrdersElementByOrderId(String email, int id);

    OrderElementDto getOrderElementById(int id);

    OrderElementDto saveOrderElement(OrderElementDto orderElement);

    OrderElementDto updateOrderElement(OrderElementDto orderElement);

}
