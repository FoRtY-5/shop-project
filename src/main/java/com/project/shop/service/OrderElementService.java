package com.project.shop.service;

import com.project.shop.model.dto.OrderDto;
import com.project.shop.model.dto.OrderElementDto;
import com.project.shop.service.implementation.OrderElementServiceImpl;

import java.util.List;

public interface OrderElementService {

    List<OrderElementDto> getOrdersElementByOrderId(int id);

    OrderElementDto getOrderElementById(int id);

    OrderElementDto saveOrderElement(OrderElementDto orderElement);

    OrderElementDto updateOrderElement(OrderElementDto orderElement);

}
