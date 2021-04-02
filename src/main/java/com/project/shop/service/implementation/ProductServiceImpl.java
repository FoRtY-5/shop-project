package com.project.shop.service.implementation;

import com.project.shop.exception.ProductNotFoundException;
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
        return modelMapper.map(getOne(id), ProductDto.class);
    }

    @SneakyThrows
    private Product getOne(int id) {
        if (productRepository.existsById(id)) {
            return productRepository.getOne(id);
        } else {
            throw new ProductNotFoundException("Product not found");
        }
    }

}
