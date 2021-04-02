package com.project.shop.service.implementation;

import com.project.shop.exception.AddressNotFoundException;
import com.project.shop.model.Address;
import com.project.shop.model.dto.AddressDto;
import com.project.shop.repository.AddressRepository;
import com.project.shop.service.AddressService;
import lombok.SneakyThrows;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AddressServiceImpl implements AddressService {

    AddressRepository addressRepository;

    ModelMapper modelMapper = new ModelMapper();

    public AddressServiceImpl(AddressRepository addressRepository) {
        this.addressRepository = addressRepository;
    }

    @Override
    public List<AddressDto> getAllAddresses(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return addressRepository.findAll(pageable).stream()
                .map(address -> modelMapper.map(address, AddressDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public List<AddressDto> getAddressesByCity(String city, int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return addressRepository.getAddressByCity(pageable, city).stream()
                .map(address -> modelMapper.map(address, AddressDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public AddressDto getAddressById(int id) {
        return modelMapper.map(getOne(id), AddressDto.class);
    }


    @SneakyThrows
    private Address getOne(int id) {
        if (addressRepository.existsById(id)) {
            return addressRepository.getOne(id);
        } else {
            throw new AddressNotFoundException("Address with following id not found: " + id);
        }

    }


}
