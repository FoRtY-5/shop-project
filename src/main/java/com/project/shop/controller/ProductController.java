package com.project.shop.controller;

import com.project.shop.model.dto.ProductDto;
import com.project.shop.service.ProductService;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/product")
public class ProductController {

    ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @RequestMapping(value = "/list", params = {"page", "size"},
            method = RequestMethod.GET)
    public List<ProductDto> getAllProducts(@RequestParam int page,
                                           @RequestParam int size) {
        return productService.getAllProducts(page, size);
    }

    @RequestMapping(value = "/list/price", params = {"page", "size", "min", "max"},
            method = RequestMethod.GET)
    public List<ProductDto> getProductsByPriceRange(@RequestParam int page,
                                                    @RequestParam int size,
                                                    @RequestParam double min,
                                                    @RequestParam double max) {
        return productService.getProductsByPriceRange(page, size, min, max);
    }

    @RequestMapping(value = "/list/name/{name}", params = {"page", "size"},
            method = RequestMethod.GET)
    public List<ProductDto> getProductByName(@PathVariable String name,
                                             @RequestParam int page,
                                             @RequestParam int size) {
        return productService.getProductByName(page, size, name);
    }

    @RequestMapping(value = "/id/{id}", method = RequestMethod.GET)
    public ProductDto getProductById(@PathVariable int id) {
        return productService.getProductById(id);
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public ProductDto createProduct(@Valid @RequestBody ProductDto product) {
        return productService.saveProduct(product);
    }

    @RequestMapping(value = "/update", method = RequestMethod.PUT)
    public ProductDto updateProduct(@Valid @RequestBody ProductDto product) {
        return productService.saveProduct(product);
    }

    //TODO sprawdzic czy dziala poprawnie
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
    public String deleteProduct(@PathVariable int id) {
        return productService.deleteProductById(id);
    }

}
