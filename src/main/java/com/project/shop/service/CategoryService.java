package com.project.shop.service;

import com.project.shop.model.dto.CategoryDto;

import java.util.List;

public interface CategoryService {

    List<CategoryDto> getAllCategories(int page, int size);

    List<CategoryDto> getCategoriesByName(int page, int size, String name);

    CategoryDto getCategoryById(int id);

    CategoryDto saveCategory(CategoryDto categoryDto);
}
