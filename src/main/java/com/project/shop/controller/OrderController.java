package com.project.shop.controller;

import com.project.shop.model.dto.OrderDto;
import com.project.shop.service.OrderService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/orders")
public class OrderController {

    OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @RequestMapping(value = "/list", params = {"page", "size"},
            method = RequestMethod.GET)
    public List<OrderDto> getAllOrders(@RequestParam int page,
                                       @RequestParam int size) {
        return orderService.getAllOrders(page, size);
    }

    @RequestMapping(value = "/id/{id}", method = RequestMethod.GET)
    public OrderDto getOrderById(@PathVariable int id) {
        return orderService.getOrderById(id);
    }
}
