package com.project.shop.bootstrap;

import com.project.shop.model.*;
import com.project.shop.model.enums.NewsLetter;
import com.project.shop.model.enums.Status;
import com.project.shop.repository.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;


@Component
public class BootStrap implements CommandLineRunner {

    Set<Role> standardRole = new HashSet<>();
    Set<Role> adminRole = new HashSet<>();
    Set<Role> sellerRole = new HashSet<>();

    UserRepository userRepository;
    RoleRepository roleRepository;
    AddressRepository addressRepository;
    OrderRepository orderRepository;
    OrderElementRepository orderElementRepository;
    ProductRepository  productRepository;

    PasswordEncoder passwordEncoder;

    public BootStrap(UserRepository userRepository,
                     RoleRepository roleRepository,
                     AddressRepository addressRepository,
                     OrderRepository orderRepository,
                     PasswordEncoder passwordEncoder,
                     OrderElementRepository orderElementRepository,
                     ProductRepository productRepository) {

        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.addressRepository = addressRepository;
        this.orderRepository = orderRepository;
        this.passwordEncoder = passwordEncoder;
        this.orderElementRepository = orderElementRepository;
        this.productRepository = productRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        loadRoles();
        loadUsers();
    }

    private void loadRoles() {
        Role regular = Role.builder()
                .roleName(Role.USER_REGULAR)
                .build();

        Role admin = Role.builder()
                .roleName(Role.USER_ADMIN).build();

        Role seller = Role.builder()
                .roleName(Role.USER_SELLER).build();

        roleRepository.save(regular);
        roleRepository.save(seller);
        roleRepository.save(admin);

        standardRole.add(regular);

        sellerRole.add(regular);
        sellerRole.add(seller);

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

        Address user3Address = Address.builder()
                .street("Jana Pawła")
                .city("Sosnowiec")
                .country("Poland")
                .postalCode("41-219").build();

        User user1 = User.builder()
                .firstName("Jan")
                .isActive(true)
                .lastName("Kowalski")
                .password(passwordEncoder.encode("sample"))
                .role(standardRole)
                .newsLetter(String.valueOf(NewsLetter.EMAIL))
                .email("jan.kowalski@test.pl")
                .address(user1Address).build();

        User user2 = User.builder()
                .firstName("Radoslaw")
                .isActive(true)
                .lastName("Blaszczykowski")
                .password(passwordEncoder.encode("test"))
                .role(adminRole)
                .newsLetter(String.valueOf(NewsLetter.EMAIL))
                .email("radolsaw.email@email.pl")
                .address(user2Address).build();

        User user3 = User.builder()
                .firstName("Jakub")
                .isActive(true)
                .lastName("Figiel")
                .password(passwordEncoder.encode(
                        "Orangutan123"))
                .role(sellerRole)
                .newsLetter(String.valueOf(NewsLetter.EMAIL))
                .email("figlu.miglu@gmail.com")
                .address(user3Address).build();

        Product product1 = Product.builder()
                .description("Fajny smartfon")
                .price(BigDecimal.valueOf(400))
                .name("Smartfon").build();

        user3.setProductSet(Set.of(product1));

        userRepository.save(user1);
        userRepository.save(user2);
        userRepository.save(user3);

        Orders orders1 = Orders.builder()
                .shipmentAddress(user1.getAddress())
                .price(BigDecimal.valueOf(899.9))
                .date("02/12/2020")
                .status(Status.IN_PROGRESS)
                .user(user1).build();

        orderRepository.save(orders1);

        OrderElement orderElement1 = OrderElement.builder()
                .orders(orders1)
                .product(product1)
                .quantity(1).build();

        orderElementRepository.save(orderElement1);

        System.out.println("##### BOOTSTRAP LOADED #####");
        System.out.println("Users in data base: " + userRepository.count());
        System.out.println("Addresses in data base: " + addressRepository.count());
        System.out.println("OrderElements int data base: " + orderElementRepository.count());
        System.out.println("Orders in data base: " + orderRepository.count());

    }

}

