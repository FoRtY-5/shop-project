package com.project.shop.controller;

import com.project.shop.model.dto.CategoryDto;
import com.project.shop.service.CategoryService;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api")
public class CategoryController {

    CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @RequestMapping(value = "/category/list", params = {"page", "size"},
            method = RequestMethod.GET)
    public List<CategoryDto> getAllCategories(@RequestParam int page,
                                              @RequestParam int size) {
        return categoryService.getAllCategories(page, size);
    }

    @RequestMapping(value = "/category/search/{name}", params = {"page", "size"},
            method = RequestMethod.GET)
    public List<CategoryDto> getCategoryByName(@PathVariable String name,
                                               @RequestParam int page,
                                               @RequestParam int size) {
        return categoryService.getCategoriesByName(page, size, name);
    }

    @RequestMapping(value = "/category/id/{id}", method = RequestMethod.GET)
    public CategoryDto getCategoryById(@PathVariable int id) {
        return categoryService.getCategoryById(id);
    }

    @RequestMapping(value = "/admin/category/create", method = RequestMethod.POST)
    public CategoryDto createCategory(@Valid @RequestBody CategoryDto category) {
        return categoryService.saveCategory(category);
    }

    @RequestMapping(value = "/admin/category/update", method = RequestMethod.PUT)
    public CategoryDto updateCategory(@Valid @RequestBody CategoryDto category) {
        return categoryService.saveCategory(category);
    }

}
