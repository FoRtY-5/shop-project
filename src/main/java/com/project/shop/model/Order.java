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
    private int id;
    @Column
    private String shipmentAddress;
    @Column
    private double price;
    @Column
    private String date;
    @Column
    private Status status;

}
