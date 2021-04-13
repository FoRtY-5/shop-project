package com.project.shop.service.implementation;

import com.project.shop.exception.CategoryNotFoundException;
import com.project.shop.model.Category;
import com.project.shop.model.dto.CategoryDto;
import com.project.shop.repository.CategoryRepository;
import com.project.shop.service.CategoryService;
import lombok.SneakyThrows;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoryServiceImpl implements CategoryService {

    CategoryRepository categoryRepository;

    ModelMapper modelMapper = new ModelMapper();

    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public List<CategoryDto> getAllCategories(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return categoryRepository.findAll(pageable).stream()
                .map(category -> modelMapper.map(category, CategoryDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public List<CategoryDto> getCategoriesByName(int page, int size, String name) {
        Pageable pageable = PageRequest.of(page, size);
        return categoryRepository.getCategoriesByNameContaining(pageable, name).stream()
                .map(category -> modelMapper.map(category, CategoryDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public CategoryDto getCategoryById(int id) {
        return modelMapper.map(getOneFromDB(id), CategoryDto.class);
    }

    @Override
    public CategoryDto saveCategory(CategoryDto categoryDto) {
        categoryRepository.save(modelMapper.map(categoryDto, Category.class));
        return categoryDto;
    }

    @SneakyThrows
    private Category getOneFromDB(int id) {
        if (categoryRepository.existsById(id)) {
            return categoryRepository.getOne(id);
        } else {
            throw new CategoryNotFoundException("Category with following id not found: " + id);
        }
    }
}
