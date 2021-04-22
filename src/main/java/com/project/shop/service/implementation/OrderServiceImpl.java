package com.project.shop.service.implementation;

import com.project.shop.exception.OrderNotFoundException;
import com.project.shop.model.Address;
import com.project.shop.model.Orders;
import com.project.shop.model.User;
import com.project.shop.model.dto.OrderDto;
import com.project.shop.repository.OrderRepository;
import com.project.shop.service.OrderService;
import lombok.SneakyThrows;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderServiceImpl implements OrderService {

    OrderRepository orderRepository;

    ModelMapper modelMapper = new ModelMapper();

    public OrderServiceImpl(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @Override
    public List<OrderDto> getAllOrders(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return orderRepository.findAll(pageable).stream()
                .map(orders -> modelMapper.map(orders, OrderDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public OrderDto getOrderById(int id) {
        return modelMapper.map(getOneFromDB(id), OrderDto.class);
    }

    @Override
    public OrderDto saveOrder(OrderDto order) {
        orderRepository.save(modelMapper.map(order, Orders.class));
        return order;
    }

    @Override
    public OrderDto updateOrder(OrderDto order) {
        Orders updatedOrder = getOneFromDB(order.getId());
        if (order.getShipmentAddress() != null)
            updatedOrder.setShipmentAddress(modelMapper.map(order.getShipmentAddress(), Address.class));
        if (order.getPrice() != null) updatedOrder.setPrice(order.getPrice());
        if (order.getStatus() != null) updatedOrder.setStatus(order.getStatus());
        if (order.getUser() != null) updatedOrder.setUser(modelMapper.map(order.getUser(), User.class));
        return modelMapper.map(updatedOrder, OrderDto.class);
    }


    @SneakyThrows
    private Orders getOneFromDB(int id) {
        if (orderRepository.existsById(id)) {
            return orderRepository.getOne(id);
        } else {
            throw new OrderNotFoundException("Order with following id not found: " + id);
        }
    }
}
