package com.project.shop.service;

import com.project.shop.model.dto.RoleDto;

import java.util.List;

public interface RoleService {

    List<RoleDto> getAllRoles(int page, int size);

    List<RoleDto> getRoleByNameContaining(String name);

    RoleDto saveRole(RoleDto role);

    RoleDto getRoleById(int id);
}
