package com.ecommerce.repository;

import com.ecommerce.model.Purchase;
import com.ecommerce.model.User;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@RequiredArgsConstructor
class UserRepositoryTest {

    private final UserRepository userRepository;
    User user;

    @BeforeEach
    void setUp() {
        List<Purchase> newPurchase = new ArrayList<>();
        user = new User(1L,"Nahuel2023","Nahuel","Rocha",
                "rocha.nahuel@hotmail.com","123123","secret1022222","USER",newPurchase);

        userRepository.save(user);

    }

    @AfterEach
    void tearDown() {

        user = null;
        userRepository.deleteAll();

    }

    @Test
    void findByFirstName_Found() {

        List<User> userList = userRepository.findByFirstName("Nahuel");



    }
}