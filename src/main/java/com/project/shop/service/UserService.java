package com.project.shop.service;

import com.project.shop.exception.UserNotFoundException;
import com.project.shop.model.dto.UserDto;
import com.project.shop.model.dto.UserSaveDto;

import java.util.List;

public interface UserService {

    List<UserDto> getAllUsers(int page, int size);

    UserDto getUserById(int id) throws UserNotFoundException;

    List<UserDto> getUsersByFirstName(String firstName, int page, int size);

    List<UserDto> getUsersByLastName(String lastName, int page, int size);

    List<UserDto> getUserByEmailContaining(String email, int page, int size);

    UserDto getUserByEmail(String email);

    UserDto saveUser(UserSaveDto user);

    UserDto updateUser(UserDto user);

    UserDto disableUserById(int id);
}
