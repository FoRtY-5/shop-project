package com.project.shop.repository;


import com.project.shop.model.Address;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AddressRepository extends JpaRepository<Address, Integer> {

    List<Address> getAddressByCity(Pageable pageable, String city);

}
