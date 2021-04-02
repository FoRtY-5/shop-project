package com.project.shop.controller;

import com.project.shop.exception.UserNotFoundException;
import com.project.shop.model.dto.UserDto;
import com.project.shop.model.dto.UserSaveDto;
import com.project.shop.service.UserService;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/user")
public class UserController {

    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping(value = "/list", params = {"page", "size"}, method = RequestMethod.GET)
    public List<UserDto> getAllUsers(@RequestParam int page,
                                     @RequestParam int size) {
        return userService.getAllUsers(page, size);
    }

    @RequestMapping(value = "/id/{id}", method = RequestMethod.GET) //FIXME REMOVE THROWS
    public UserDto getUserById(@PathVariable int id) throws UserNotFoundException {
        return userService.getUserById(id);
    }

    @RequestMapping(value = "/firstname/{firstName}", params = {"page", "size"},
            method = RequestMethod.GET)
    public List<UserDto> getUsersByFirstName(@PathVariable String firstName,
                                            @RequestParam int page,
                                            @RequestParam int size) {
        return userService.getUsersByFirstName(firstName, page, size);
    }

    @RequestMapping(value = "/lastname/{lastName}", params = {"page", "size"},
    method = RequestMethod.GET)
    public List<UserDto> getUsersByLastName(@PathVariable String lastName,
                                            @RequestParam int page,
                                            @RequestParam int size) {
        return userService.getUsersByLastName(lastName, page, size);
    }

    @RequestMapping(value = "/email/{email}", method = RequestMethod.GET)
    public UserDto getUserByEmail(@PathVariable String email) {
        return userService.getUserByEmail(email);
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public UserDto createUser(@Valid @RequestBody UserSaveDto user) {
        return userService.createUser(user);
    }



}
