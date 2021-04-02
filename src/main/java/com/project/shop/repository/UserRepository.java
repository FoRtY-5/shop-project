package com.project.shop.repository;


import com.project.shop.model.User;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Integer> {

    List<User> getUsersByFirstName(Pageable pageable, String firstName);

    List<User> getUsersByLastName(Pageable pageable, String lastName);

    User getUserByEmail(String email);

}
