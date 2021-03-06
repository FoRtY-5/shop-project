package com.project.shop.controller;

import com.project.shop.model.dto.OrderDto;
import com.project.shop.service.OrderService;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/api")
public class OrderController {

    OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @RequestMapping(value = "/admin/orders/list", params = {"page", "size"},
            method = RequestMethod.GET)
    public List<OrderDto> getAllOrders(@RequestParam int page,
                                       @RequestParam int size) {
        return orderService.getAllOrders(page, size);
    }

    @RequestMapping(value = "/admin/orders/id/{id}", method = RequestMethod.GET)
    public OrderDto getOrderById(@PathVariable int id) {
        return orderService.getOrderById(id);
    }

    @RequestMapping(value = "/regular/orders/create", method = RequestMethod.POST)
    public OrderDto createOrder(@Valid @RequestBody OrderDto order) {
        return orderService.saveOrder(order);
    }

    @RequestMapping(value = "/regular/orders/update", method = RequestMethod.PUT)
    public OrderDto updateOrder(OrderDto order) {
        return orderService.updateOrder(order);
    }

    @RequestMapping(value = "/regular/orders/me", params = {"page", "size"}
            , method = RequestMethod.GET)
    public List<OrderDto> getOrdersByUser(Principal principal,
                                          @RequestParam int page,
                                          @RequestParam int size) {
        return orderService.getOrdersByUserEmail(principal.getName(), page, size);
    }

}
