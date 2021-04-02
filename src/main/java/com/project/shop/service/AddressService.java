package com.project.shop.service;

import com.project.shop.model.dto.AddressDto;

import java.util.List;

public interface AddressService {

    List<AddressDto> getAllAddresses(int page, int size);

    List<AddressDto> getAddressesByCity(String city, int page, int size);

    AddressDto getAddressById(int id);
}
