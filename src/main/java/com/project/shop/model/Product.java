package com.project.shop.model;

import lombok.*;

import javax.persistence.*;
import java.util.Set;

@Data
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "PRODUCT")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "PR_ID")
    private int id;
    @Column(name = "PR_NAME")
    private String name;
    @Column(name = "PR_DESCRIPTION")
    private String description;
    @Column(name = "PR_MINIATUREURL")
    private String miniatureUrl;
    @Column(name = "PR_PRICE")
    private double price;
    @ManyToMany
    @JoinTable(name = "PRODUCT_CATEGORY", joinColumns = @JoinColumn(name = "PRODUCT_PR_ID"),
    inverseJoinColumns = @JoinColumn(name = "CATEGORY_CA_ID"))
    private Set<Category> category;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "PR_US_ID")
    private User user;
}
