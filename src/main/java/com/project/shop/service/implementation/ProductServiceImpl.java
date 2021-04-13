package com.project.shop.service.implementation;

import com.project.shop.exception.ProductNotFoundException;
import com.project.shop.model.Category;
import com.project.shop.model.Product;
import com.project.shop.model.dto.ProductDto;
import com.project.shop.repository.ProductRepository;
import com.project.shop.service.ProductService;
import lombok.SneakyThrows;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService {

    ProductRepository productRepository;

    ModelMapper modelMapper = new ModelMapper();

    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public List<ProductDto> getAllProducts(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return productRepository.findAll(pageable).stream()
                .map(product -> modelMapper.map(product, ProductDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public List<ProductDto> getProductsByPriceRange(int page, int size, double min, double max) {
        Pageable pageable = PageRequest.of(page, size);
        return productRepository.getAllByPriceBetween(pageable, min, max)
                .stream().map(product -> modelMapper.map(product, ProductDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public List<ProductDto> getProductByName(int page, int size, String name) {
        Pageable pageable = PageRequest.of(page, size);
        return productRepository.getProductsByNameContaining(pageable, name)
                .stream().map(product -> modelMapper.map(product, ProductDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public ProductDto getProductById(int id) {
        return modelMapper.map(getOneFromDB(id), ProductDto.class);
    }

    @Override
    public ProductDto saveProduct(ProductDto product) {
        productRepository.save(modelMapper.map(product, Product.class));
        return product;
    }

    @Override
    public ProductDto updateProduct(ProductDto product) {
        Product updatedProduct = getOneFromDB(product.getId());
        if (product.getName() != null) updatedProduct.setName(product.getName());
        if (product.getDescription() != null) updatedProduct.setDescription(product.getDescription());
        if (product.getMiniatureUrl() != null) updatedProduct.setMiniatureUrl(product.getMiniatureUrl());
        if (product.getPrice() != null) updatedProduct.setPrice(product.getPrice());
        if (product.getCategory() != null)
            updatedProduct.setCategory(product.getCategory().stream().map(
                    categoryDto -> modelMapper.map(categoryDto, Category.class))
                    .collect(Collectors.toSet()));
        return modelMapper.map(updatedProduct, ProductDto.class);
    }

    @SneakyThrows
    private Product getOneFromDB(int id) {
        if (productRepository.existsById(id)) {
            return productRepository.getOne(id);
        } else {
            throw new ProductNotFoundException("Product not found");
        }
    }

}
