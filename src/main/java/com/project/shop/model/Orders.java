package com.project.shop.model;

import com.project.shop.model.enums.Status;
import lombok.*;

import javax.persistence.*;

@Data
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "ORDERS")
public class Orders {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "OR_ID")
    private int id;

    @ManyToOne(fetch = FetchType.EAGER)
    private Address shipmentAddress;

    @Column(name = "OR_PRICE")
    private double price;

    @Column(name = "OR_DATE")
    private String date;

    @Column(name = "OR_STATUS")
    private Status status;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "OR_US_ID", referencedColumnName = "US_ID")
    private User user;

}
