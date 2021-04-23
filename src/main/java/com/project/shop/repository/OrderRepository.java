package com.project.shop.repository;

import com.project.shop.model.Orders;
import com.project.shop.model.User;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface OrderRepository extends JpaRepository<Orders, Integer> {

    List<Orders> getOrdersByUserId(Pageable pageable, int id);

    boolean existsOrdersByUserEmailAndId(String email, int id);
}
