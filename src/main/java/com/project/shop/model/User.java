package com.project.shop.model;

import com.project.shop.model.enums.NewsLetter;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.Set;


@Data
@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "USER")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "US_ID")
    private int id;

    @Column(name = "US_FIRSTNAME")
    @NotEmpty
    private String firstName;

    @Column(name = "US_LASTNAME")
    @NotEmpty
    private String lastName;

    @Column(name = "US_EMAIL")
    @NotEmpty
    private String email;

    @Column(name = "US_PASSWORD")
    @NotEmpty
    private String password;

    @OneToOne
    @NotNull
    private Address address;

    @Column(name = "US_NEWSLETTER")
    private String newsLetter;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "USER_ROLE", joinColumns = @JoinColumn(name = "US_ID"),
    inverseJoinColumns = @JoinColumn(name = "RO_ID"))
    private Set<Role> role = new HashSet<>();

    @OneToMany(fetch = FetchType.EAGER)
    @JoinColumn(name = "USER_ID")
    private Set<Product> productSet = new HashSet<>();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        User user = (User) o;

        return id == user.id;
    }

    @Override
    public int hashCode() {
        return id;
    }
}
