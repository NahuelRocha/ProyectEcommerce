package com.ecommerce.dto;

import com.ecommerce.model.Purchase;

import java.util.List;

public record UserDTO(
        Long id,
        String username,
        String firstName,
        String lastName,
        String email,
        String phone,
        String role,
        List<Purchase> purchase

) {}
