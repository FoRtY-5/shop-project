package com.project.shop.service.implementation;

import com.project.shop.exception.RoleNotFoundException;
import com.project.shop.model.Role;
import com.project.shop.model.dto.RoleDto;
import com.project.shop.repository.RoleRepository;
import com.project.shop.service.RoleService;
import lombok.SneakyThrows;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RoleServiceImpl implements RoleService {

    RoleRepository roleRepository;
    ModelMapper modelMapper = new ModelMapper();


    public RoleServiceImpl(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public List<RoleDto> getAllRoles(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return roleRepository.findAll(pageable).stream()
                .map(role -> modelMapper.map(role, RoleDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public List<RoleDto> getRoleByName(String name) {
        return roleRepository.getRolesByRoleNameContaining(name).stream()
                .map(role -> modelMapper.map(role, RoleDto.class))
                .collect(Collectors.toList());
    }


    @Override
    public RoleDto saveRole(RoleDto role) {
        roleRepository.save(modelMapper.map(role, Role.class));
        return role;
    }

    @Override
    public RoleDto getRoleById(int id) {
        return modelMapper.map(getOneFromDB(id), RoleDto.class);
    }

    @SneakyThrows
    private Role getOneFromDB(int id) {
        if (roleRepository.existsById(id)) {
            return roleRepository.getOne(id);
        } else {
            throw new RoleNotFoundException("Role with following id not found: " + id);
        }
    }
}
