package com.project.shop.bootstrap;

import com.project.shop.model.Address;
import com.project.shop.model.Role;
import com.project.shop.model.User;
import com.project.shop.model.enums.NewsLetter;
import com.project.shop.repository.AddressRepository;
import com.project.shop.repository.RoleRepository;
import com.project.shop.repository.UserRepository;
import org.hibernate.Session;
import org.hibernate.internal.SessionImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;


@Component
public class BootStrap implements CommandLineRunner {

    Set<Role> standardRole = new HashSet<>();

    UserRepository userRepository;
    RoleRepository roleRepository;
    AddressRepository addressRepository;

    public BootStrap(UserRepository userRepository, RoleRepository roleRepository,
                     AddressRepository addressRepository) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.addressRepository = addressRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        loadEntities();
    }

    private void loadEntities() {
        Role regularRole = Role.builder()
                .roleName("Regular")
                .build();

        roleRepository.save(regularRole);

        standardRole.add(regularRole);

        Address user1Address = Address.builder()
                .street("Aleje Jerozolimskie")
                .city("Warsaw")
                .country("Poland")
                .postalCode("00-001").build();

        addressRepository.save(user1Address);

        User user = User.builder()
                .firstName("Jan")
                .lastName("Kowalski")
                .password("sample")
                .role(standardRole)
                .email("jan.kowalski@test.pl")
                .address(user1Address).build();

        userRepository.save(user);

        System.out.println("##### BOOTSTRAP LOADED #####");
        System.out.println("Users in data base: " + userRepository.count());

    }

}
