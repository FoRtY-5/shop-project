package com.project.shop.service.implementation;

import com.project.shop.exception.UserNotFoundException;
import com.project.shop.model.Address;
import com.project.shop.model.Role;
import com.project.shop.model.User;
import com.project.shop.model.dto.UserDto;
import com.project.shop.model.dto.UserSaveDto;
import com.project.shop.model.enums.NewsLetter;
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
        return modelMapper.map(getOneFromDB(id), UserDto.class);
    }

    @Override
    public List<UserDto> getUsersByFirstName(String firstName, int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return userRepository.getUsersByFirstNameContaining(pageable, firstName).stream()
                .map(user -> modelMapper.map(user, UserDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public List<UserDto> getUsersByLastName(String lastName, int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return userRepository.getUsersByLastNameContaining(pageable, lastName).stream()
                .map(user -> modelMapper.map(user, UserDto.class))
                .collect(Collectors.toList());
    }

    @SneakyThrows
    @Override
    public UserDto getUserByEmail(String email) {
        if (userRepository.existsUserByEmailContaining(email)) {
            return modelMapper.map(userRepository.getUserByEmailContaining(email), UserDto.class);
        } else {
            throw new UserNotFoundException("User not found");
        }
    }

    @Override
    @Transactional
    public UserDto saveUser(UserSaveDto user) {
        User savedUser = modelMapper.map(user, User.class);
        savedUser.setRole(Set.of(roleRepository.getOne(1)));
        savedUser.setActive(true);
        userRepository.save(savedUser);
        return modelMapper.map(savedUser, UserDto.class);
    }

    @Override
    public UserDto updateUser(UserDto user) {
        User updatedUser = getOneFromDB(user.getId());
        if (user.getFirstName() != null) updatedUser.setFirstName(user.getFirstName());
        if(user.getLastName() != null) updatedUser.setLastName(user.getLastName());
        if (user.getEmail() != null) updatedUser.setEmail(user.getEmail());
        if (user.getPassword() != null) updatedUser.setPassword(user.getPassword());
        if (user.getAddress() != null) updatedUser.setAddress(modelMapper.map(user.getAddress(), Address.class));
        if (user.getNewsletter() != null) updatedUser.setNewsLetter(user.getNewsletter());
        return modelMapper.map(updatedUser, UserDto.class);
    }

    @Override
    public UserDto disableUserById(int id) {
        User user = getOneFromDB(id);
        user.setActive(false);
        return modelMapper.map(user, UserDto.class);
    }

    @SneakyThrows
    private User getOneFromDB(int id) {
        if (userRepository.existsById(id)) {
            return userRepository.getOne(id);
        } else {
            throw new UserNotFoundException("User with following id not found: " + id);
        }
    }
}
