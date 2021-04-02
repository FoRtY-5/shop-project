package com.project.shop.repository;


import com.project.shop.model.Product;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Integer> {

    List<Product> getAllByPriceBetween(Pageable pageable, double min, double max);

    List<Product> getProductsByNameContaining(Pageable pageable, String name);

}
