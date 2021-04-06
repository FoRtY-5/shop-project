package com.project.shop.model;

import lombok.*;

import javax.persistence.*;

@Data
@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "ADDRESS")
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "AD_ID")
    private int id;

    @Column(name = "AD_STREET")
    private String street;

    @Column(name = "AD_CITY")
    private String city;

    @Column(name = "AD_COUNTRY")
    private String country;

    @Column(name = "AD_POSTALCODE")
    private String postalCode;

}
