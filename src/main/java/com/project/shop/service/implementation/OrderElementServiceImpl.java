package com.project.shop.service.implementation;

import com.project.shop.exception.OrderElementNotFoundException;
import com.project.shop.model.OrderElement;
import com.project.shop.model.Orders;
import com.project.shop.model.Product;
import com.project.shop.model.dto.OrderElementDto;
import com.project.shop.repository.OrderElementRepository;
import com.project.shop.service.OrderElementService;
import lombok.SneakyThrows;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderElementServiceImpl implements OrderElementService {

    OrderElementRepository orderElementRepository;

    ModelMapper modelMapper = new ModelMapper();

    public OrderElementServiceImpl(OrderElementRepository orderElementRepository) {
        this.orderElementRepository = orderElementRepository;
    }

    @Override
    public List<OrderElementDto> getOrdersElementByOrderId(int id) {
        return orderElementRepository.getAllByOrdersId(id).stream()
                .map(orderElement -> modelMapper.map(orderElement, OrderElementDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public OrderElementDto getOrderElementById(int id) {
        return modelMapper.map(getOneFromDB(id), OrderElementDto.class);
    }

    @Override
    public OrderElementDto saveOrderElement(OrderElementDto orderElement) {
        orderElementRepository.save(modelMapper.map(orderElement, OrderElement.class));
        return orderElement;
    }

    @Override
    public OrderElementDto updateOrderElement(OrderElementDto orderElement) {
        OrderElement updatedOrderElement = getOneFromDB(orderElement.getId());
        if (orderElement.getQuantity() != updatedOrderElement.getQuantity())
            updatedOrderElement.setQuantity(orderElement.getQuantity());
        if (orderElement.getOrder() != null)
            updatedOrderElement.setOrders(modelMapper.map(orderElement.getOrder(), Orders.class));
        if (orderElement.getProduct() != null)
            updatedOrderElement.setProduct(modelMapper.map(orderElement.getProduct(), Product.class));
        return modelMapper.map(updatedOrderElement, OrderElementDto.class);
    }

    @SneakyThrows
    private OrderElement getOneFromDB(int id) {
        if (orderElementRepository.existsById(id)) {
            return orderElementRepository.getOne(id);
        } else {
            throw new OrderElementNotFoundException("OrderElement with following id not found: " + id);
        }
    }
}
