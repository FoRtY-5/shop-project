package com.project.shop.repository;

import com.project.shop.model.Role;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RoleRepository extends JpaRepository<Role, Integer> {

    List<Role> getRolesByRoleNameContaining(String name);

}
