package com.ecommerce.repository;

import com.ecommerce.model.Purchase;
import com.ecommerce.model.User;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@DataJpaTest
class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;
    User userEcommerce;

    @BeforeEach
    void setUp() {
        List<Purchase> newPurchase = new ArrayList<>();
        userEcommerce = new User(1L,"Nahuel2023","Nahuel","Rocha",
                "rocha.nahuel@hotmail.com","123123","secret1022222",
                "USER",newPurchase);

        userRepository.save(userEcommerce);

    }

    @AfterEach
    void tearDown() {

        userEcommerce = null;
        userRepository.deleteAll();

    }

    @Test
    void findByFirstName_Found() {

        List<User> userList = userRepository.findByFirstName("Nahuel");

        assertThat(userList.get(0).getId()).isEqualTo(userEcommerce.getId());
        assertThat(userList.get(0).getFirstName()).isEqualTo(userEcommerce.getFirstName());
        assertThat(userList.get(0).getUsername()).isEqualTo(userEcommerce.getUsername());

    }





}