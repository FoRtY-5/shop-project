package com.project.shop.controller;

import com.project.shop.model.dto.AddressDto;
import com.project.shop.repository.AddressRepository;
import com.project.shop.service.AddressService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/address")
public class AddressController {

    AddressService addressService;

    public AddressController(AddressService addressService) {
        this.addressService = addressService;
    }

    @RequestMapping(value = "/list", params = {"page", "size"},
            method = RequestMethod.GET)
    public List<AddressDto> getAllAddresses(@RequestParam int page,
                                            @RequestParam int size) {
        return addressService.getAllAddresses(page, size);
    }

    @RequestMapping(value = "/city/{cityName}", params = {"page", "size"},
            method = RequestMethod.GET)
    public List<AddressDto> getAddressesByCity(@PathVariable String cityName,
                                               @RequestParam int page,
                                               @RequestParam int size) {
        return addressService.getAddressesByCity(cityName, page, size);
    }

    @RequestMapping(value = "/id/{id}", method = RequestMethod.GET)
    public AddressDto getAddressById(@PathVariable int id) {
        return addressService.getAddressById(id);
    }

}
