package com.project.shop.controller;

import com.project.shop.model.dto.RoleDto;
import com.project.shop.service.RoleService;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api")
public class RoleController {

    RoleService roleService;

    public RoleController(RoleService roleService) {
        this.roleService = roleService;
    }

    @RequestMapping(value = "/admin/role/list", params = {"page", "size"},
            method = RequestMethod.GET)
    public List<RoleDto> getAllRoles(@RequestParam int page,
                                     @RequestParam int size) {
        return roleService.getAllRoles(page, size);
    }

    @RequestMapping(value = "/admin/role/list/name/{name}", method = RequestMethod.GET)
    public List<RoleDto> getRolesByName(@PathVariable String name) {
        return roleService.getRoleByNameContaining(name);
    }

    @RequestMapping(value = "/admin/role/id/{id}", method = RequestMethod.GET)
    public RoleDto getRoleById(@PathVariable int id) {
        return roleService.getRoleById(id);
    }

    @RequestMapping(value = "/admin/role/create", method = RequestMethod.POST)
    public RoleDto createRole(@Valid @RequestBody RoleDto role) {
        return roleService.saveRole(role);
    }

    @RequestMapping(value = "/admin/role/update", method = RequestMethod.PUT)
    public RoleDto updateRole(@Valid @RequestBody RoleDto role) {
        return roleService.saveRole(role);
    }

}
