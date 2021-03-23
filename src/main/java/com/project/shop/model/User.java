package com.project.shop.model;

import com.project.shop.model.enums.NewsLetter;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;


@Data
@Entity
@Getter
@Setter
@Table(name = "USER")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column
    private String firstName;
    @Column
    private String lastName;
    @Column
    private String email;
    @Column
    private String password;
    @OneToOne
    private Address address;
    @ManyToOne
    private Role role;
    @Column
    private NewsLetter newsLetter;

}
