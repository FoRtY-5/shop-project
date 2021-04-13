package com.project.shop.service;

import com.project.shop.model.dto.ProductDto;

import java.util.List;

public interface ProductService {

    List<ProductDto> getAllProducts(int page, int size);

    List<ProductDto> getProductsByPriceRange(int page, int size, double min, double max);

    List<ProductDto> getProductByName(int page, int size, String name);

    ProductDto getProductById(int id);

    ProductDto saveProduct(ProductDto product);

    ProductDto updateProduct(ProductDto product);

}
