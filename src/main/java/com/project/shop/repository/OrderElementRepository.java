package com.project.shop.repository;

import com.project.shop.model.OrderElement;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderElementRepository extends JpaRepository<OrderElement, Integer> {

    List<OrderElement> getAllByOrdersId(int id);

}
