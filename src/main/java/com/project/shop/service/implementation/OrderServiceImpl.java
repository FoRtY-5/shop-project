package com.project.shop.service.implementation;

import com.project.shop.exception.OrderNotFoundException;
import com.project.shop.model.Orders;
import com.project.shop.model.dto.OrderDto;
import com.project.shop.repository.OrderRepository;
import com.project.shop.repository.UserRepository;
import com.project.shop.service.OrderService;
import com.project.shop.service.UserService;
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

    public OrderServiceImpl(OrderRepository orderRepository, UserService userService) {
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
        return modelMapper.map(getOne(id), OrderDto.class);
    }


    @SneakyThrows
    private Orders getOne(int id) {
        if (orderRepository.existsById(id)) {
            return orderRepository.getOne(id);
        } else {
            throw new OrderNotFoundException("Order with following id not found: " + id);
        }
    }
}
