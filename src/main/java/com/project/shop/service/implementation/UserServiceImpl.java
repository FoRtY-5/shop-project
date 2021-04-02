package com.project.shop.service.implementation;

import com.project.shop.exception.UserNotFoundException;
import com.project.shop.model.User;
import com.project.shop.model.dto.UserDto;
import com.project.shop.model.dto.UserSaveDto;
import com.project.shop.repository.AddressRepository;
import com.project.shop.repository.RoleRepository;
import com.project.shop.repository.UserRepository;
import com.project.shop.service.UserService;

import lombok.SneakyThrows;

import org.modelmapper.ModelMapper;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;
    private AddressRepository addressRepository;
    private RoleRepository roleRepository;

    private ModelMapper modelMapper = new ModelMapper();

    public UserServiceImpl(UserRepository userRepository, AddressRepository addressRepository, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.addressRepository = addressRepository;
        this.roleRepository = roleRepository;
    }

    @Override
    public List<UserDto> getAllUsers(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return userRepository.findAll(pageable).stream()
                .map(user -> modelMapper.map(user, UserDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public UserDto getUserById(int id) {
        return modelMapper.map(getOne(id), UserDto.class);
    }

    @Override
    public List<UserDto> getUsersByFirstName(String firstName, int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return userRepository.getUsersByFirstName(pageable, firstName).stream()
                .map(user -> modelMapper.map(user, UserDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public List<UserDto> getUsersByLastName(String lastName, int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return userRepository.getUsersByLastName(pageable, lastName).stream()
                .map(user -> modelMapper.map(user, UserDto.class))
                .collect(Collectors.toList());
    }

    @SneakyThrows
    @Override
    public UserDto getUserByEmail(String email) {
        if (userRepository.getUserByEmail(email) != null) {
            return modelMapper.map(userRepository.getUserByEmail(email), UserDto.class);
        } else {
            throw new UserNotFoundException("User not found");
        }
    }

    @Override
    @Transactional
    public UserDto createUser(UserSaveDto user) {
        User savedUser = modelMapper.map(user, User.class);
        savedUser.setRole(Set.of(roleRepository.getOne(1)));
        userRepository.save(savedUser);
        return modelMapper.map(savedUser, UserDto.class);
    }

    @SneakyThrows
    private User getOne(int id) {
        if (userRepository.existsById(id)) {
            return userRepository.getOne(id);
        } else {
            throw new UserNotFoundException("User with following id not found: " + id);
        }
    }
}
