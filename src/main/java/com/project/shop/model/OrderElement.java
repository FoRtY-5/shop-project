package com.project.shop.model;


import lombok.*;

import javax.persistence.*;

@Data
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "ORDER_ELEMENTS")
public class OrderElement {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @Column
    private int quantity;
    @ManyToOne
    private Order order;
    @ManyToOne
    private Product product;

}

