package com.project.shop.model;


import lombok.*;

import javax.persistence.*;

@Data
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "ORDER_ELEMENTS")
public class OrderElement {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "OE_ID")
    private int id;

    @Column(name = "OE_QUANTITY")
    private int quantity;

    @ManyToOne
    private Orders orders;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "OE_PR_ID")
    private Product product;

}

