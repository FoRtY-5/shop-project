package com.project.shop.repository;

import com.project.shop.model.Category;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CategoryRepository extends JpaRepository<Category, Integer> {

    List<Category> getCategoriesByNameContaining(Pageable pageable, String name);

}
