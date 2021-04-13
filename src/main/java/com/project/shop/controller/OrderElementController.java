package com.project.shop.controller;

import com.project.shop.model.dto.OrderDto;
import com.project.shop.model.dto.OrderElementDto;
import com.project.shop.service.OrderElementService;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/orderelement")
        public class OrderElementController{

        OrderElementService orderElementService;

        public OrderElementController(OrderElementService orderElementService) {
        this.orderElementService = orderElementService;
    }

    @RequestMapping(value = "/id/{id}", method = RequestMethod.GET)
    public OrderElementDto getOrderElementById(@PathVariable int id) {
        return orderElementService.getOrderElementById(id);
    }

    @RequestMapping(value = "/order", method = RequestMethod.GET)
    public OrderElementDto getOrderElementByOrder(@Valid @RequestBody OrderDto order) {
        return orderElementService.getOrderElementByOrder(order);
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public OrderElementDto createOrderElement(@Valid @RequestBody OrderElementDto orderElement) {
        return orderElementService.saveOrderElement(orderElement);
    }

    @RequestMapping(value = "/update", method = RequestMethod.PUT)
    public OrderElementDto updateOrderElement(@Valid @RequestBody OrderElementDto orderElement) {
        return orderElementService.saveOrderElement(orderElement);
    }

//    @RequestMapping(value = "/delete", method = RequestMethod.DELETE)
//    public OrderElementDto deleteOrderElement(@Valid @RequestBody OrderElementDto orderElement) {
//        return orderElementService.deleteOrderElement(orderElement);
//    }


}
