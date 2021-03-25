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
@Table(name = "ORDERS")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "OR_ID")
    private int id;

    @Column(name = "OR_SHIPMENTADDRESS")
    private String shipmentAddress;

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
