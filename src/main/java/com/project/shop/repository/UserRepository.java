package com.project.shop.repository;


import com.project.shop.model.User;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Integer> {

    List<User> getUsersByFirstNameContaining(Pageable pageable, String firstName);

    List<User> getUsersByLastNameContaining(Pageable pageable, String lastName);

    User getUserByEmailContaining(String email);

    User getTopByOrderByIdDesc();

    boolean existsUsersByFirstNameContaining(String firstName);

    boolean existsUsersByLastNameContaining(String lastName);

    boolean existsUserByEmailContaining(String email);



}
