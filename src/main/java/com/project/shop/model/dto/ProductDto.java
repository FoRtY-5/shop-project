package com.project.shop.model.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.jdbc.support.incrementer.HanaSequenceMaxValueIncrementer;

import java.util.HashSet;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductDto {

    private int id;

    private String name;

    private String description;

    private String miniatureUrl;

    private double price;

    private Set<CategoryDto> category = new HashSet<>();


}
