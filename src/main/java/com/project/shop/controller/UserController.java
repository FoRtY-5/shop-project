package com.project.shop.controller;

import com.project.shop.exception.UserNotFoundException;
import com.project.shop.model.dto.UserDto;
import com.project.shop.model.dto.UserSaveDto;
import com.project.shop.service.UserService;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/api")
public class UserController {

    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping(value = "/admin/user/list", params = {"page", "size"}, method = RequestMethod.GET)
    public List<UserDto> getAllUsers(@RequestParam int page,
                                     @RequestParam int size) {
        return userService.getAllUsers(page, size);
    }

    @RequestMapping(value = "/admin/user/id/{id}", method = RequestMethod.GET) //FIXME REMOVE THROWS
    public UserDto getUserById(@PathVariable int id) throws UserNotFoundException {
        return userService.getUserById(id);
    }

    @RequestMapping(value = "/admin/user/firstname/{firstName}", params = {"page", "size"},
            method = RequestMethod.GET)
    public List<UserDto> getUsersByFirstName(@PathVariable String firstName,
                                             @RequestParam int page,
                                             @RequestParam int size) {
        return userService.getUsersByFirstName(firstName, page, size);
    }

    @RequestMapping(value = "/admin/user/lastname/{lastName}", params = {"page", "size"},
            method = RequestMethod.GET)
    public List<UserDto> getUsersByLastName(@PathVariable String lastName,
                                            @RequestParam int page,
                                            @RequestParam int size) {
        return userService.getUsersByLastName(lastName, page, size);
    }

    @RequestMapping(value = "/admin/user/email/{email}", params = {"page", "size"},
            method = RequestMethod.GET)
    public List<UserDto> getUserByEmail(@PathVariable String email,
                                        @RequestParam int page,
                                        @RequestParam int size) {
        return userService.getUserByEmailContaining(email, page, size);
    }

    @RequestMapping(value = "/user/register", method = RequestMethod.POST)
    public UserDto createUser(@Valid @RequestBody UserSaveDto user) {
        return userService.saveUser(user);
    }

    @RequestMapping(value = "/regular/user/update", method = RequestMethod.PUT)
    public UserDto updateUser(@Valid @RequestBody UserDto user) {
        return userService.updateUser(user);
    }

    @RequestMapping(value = "/regular/user/delete/{id}", method = RequestMethod.DELETE)
    public UserDto deleteUser(@PathVariable int id) {
        return userService.disableUserById(id);
    }

    @RequestMapping(value = "/regular/user/me", method = RequestMethod.GET)
    public UserDto getUser(Principal principal) {
        return userService.getUserByEmail(principal.getName());
    }

}
