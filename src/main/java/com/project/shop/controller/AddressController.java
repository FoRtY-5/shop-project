package com.project.shop.controller;

import com.project.shop.model.Role;
import com.project.shop.model.dto.AddressDto;
import com.project.shop.service.AddressService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.RolesAllowed;
import java.util.List;

@RestController
@RequestMapping("/api")
public class AddressController {

    AddressService addressService;

    public AddressController(AddressService addressService) {
        this.addressService = addressService;
    }

    @RolesAllowed(Role.USER_ADMIN)
    @RequestMapping(value = "/admin/address/list", params = {"page", "size"},
            method = RequestMethod.GET)
    public List<AddressDto> getAllAddresses(@RequestParam int page,
                                            @RequestParam int size) {
        return addressService.getAllAddresses(page, size);
    }

    @RolesAllowed(Role.USER_ADMIN)
    @RequestMapping(value = "/admin/address/city/{cityName}", params = {"page", "size"},
            method = RequestMethod.GET)
    public List<AddressDto> getAddressesByCity(@PathVariable String cityName,
                                               @RequestParam int page,
                                               @RequestParam int size) {
        return addressService.getAddressesByCity(cityName, page, size);
    }

    @RolesAllowed(Role.USER_ADMIN)
    @RequestMapping(value = "/admin/address/id/{id}", method = RequestMethod.GET)
    public AddressDto getAddressById(@PathVariable int id) {
        return addressService.getAddressById(id);
    }

}
