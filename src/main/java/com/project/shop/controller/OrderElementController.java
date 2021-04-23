package com.project.shop.controller;

import com.project.shop.model.dto.OrderElementDto;
import com.project.shop.service.OrderElementService;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/api")
public class OrderElementController {

    OrderElementService orderElementService;

    public OrderElementController(OrderElementService orderElementService) {
        this.orderElementService = orderElementService;
    }

    @RequestMapping(value = "/regular/orderelement/id/{id}", method = RequestMethod.GET)
    public OrderElementDto getOrderElementById(@PathVariable int id) {
        return orderElementService.getOrderElementById(id);
    }

    @RequestMapping(value = "/regular/orderelement/create", method = RequestMethod.POST)
    public OrderElementDto createOrderElement(@Valid @RequestBody OrderElementDto orderElement) {
        return orderElementService.saveOrderElement(orderElement);
    }

    @RequestMapping(value = "/admin/orderelement/update", method = RequestMethod.PUT)
    public OrderElementDto updateOrderElement(@Valid @RequestBody OrderElementDto orderElement) {
        return orderElementService.saveOrderElement(orderElement);
    }

    @RequestMapping(value = "/regular/orderelement/order/{id}", method = RequestMethod.GET)
    public List<OrderElementDto> getOrderElementByOrder(@PathVariable int id,
                                                        Principal principal) {
        return orderElementService.getOrdersElementByOrderId(principal.getName(), id);
    }

}
