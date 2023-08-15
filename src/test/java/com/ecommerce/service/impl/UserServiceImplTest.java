package com.ecommerce.service.impl;

import com.ecommerce.dto.UserDTO;
import com.ecommerce.mapper.Mappers;
import com.ecommerce.model.Purchase;
import com.ecommerce.model.User;
import com.ecommerce.repository.UserRepository;
import com.ecommerce.service.UserService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class UserServiceImplTest {

    @Mock
    private UserRepository userRepository;
    private UserService userService;
    AutoCloseable autoCloseable;
    User userEcommerce;

    @BeforeEach
    void setUp() {
        autoCloseable = MockitoAnnotations.openMocks(this);
        userService = new UserServiceImpl(userRepository);
        List<Purchase> newPurchase = new ArrayList<>();
        userEcommerce = new User(1L,"Nahuel2023","Nahuel","Rocha",
                "rocha.nahuel@hotmail.com","123123","secret1022222",
                "USER",newPurchase);
    }

    @AfterEach
    void tearDown() throws Exception {
        autoCloseable.close();
    }

    @Test
    void createUser() {

        when(userRepository.save(userEcommerce)).thenReturn(userEcommerce);

        UserDTO userDTO = Mappers.userToUserDTO(userEcommerce);

        assertThat(userService.createUser(userEcommerce)).isEqualTo(userDTO);

    }

    @Test
    void getAllUsers() {
    }

    @Test
    void getUser() {
    }

    @Test
    void deleteUser() {
    }

    @Test
    void updateUser() {
    }

    @Test
    void findByUsername() {
    }

    @Test
    void findByFirstName() {
    }
}