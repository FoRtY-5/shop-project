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
    Set<Role> adminRole = new HashSet<>();

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
        loadRoles();
        loadUsers();
    }

    private void loadRoles() {
        Role regular = Role.builder()
                .roleName("Regular")
                .build();

        Role admin = Role.builder()
                .roleName("Admin").build();

        Role seller = Role.builder()
                .roleName("Seller").build();

        roleRepository.save(regular);
        roleRepository.save(seller);
        roleRepository.save(admin);

        standardRole.add(regular);

        adminRole.add(regular);
        adminRole.add(seller);
        adminRole.add(admin);
    }

    private void loadUsers() {

        Address user1Address = Address.builder()
                .street("Aleje Jerozolimskie")
                .city("Warsaw")
                .country("Poland")
                .postalCode("00-001").build();

        Address user2Address = Address.builder()
                .street("Chmielna")
                .city("Gdansk")
                .country("Poland")
                .postalCode("80-001").build();

        addressRepository.save(user1Address);
        addressRepository.save(user2Address);

        User user1 = User.builder()
                .firstName("Jan")
                .lastName("Kowalski")
                .password("sample")
                .role(standardRole)
                .email("jan.kowalski@test.pl")
                .address(user1Address).build();

        User user2 = User.builder()
                .firstName("Radoslaw")
                .lastName("Blaszczykowski")
                .password("test")
                .role(adminRole)
                .email("radolsaw.email@email.pl")
                .address(user2Address).build();

        userRepository.save(user1);
        userRepository.save(user2);

        System.out.println("##### BOOTSTRAP LOADED #####");
        System.out.println("Users in data base: " + userRepository.count());

    }

}

