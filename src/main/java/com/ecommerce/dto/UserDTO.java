package com.ecommerce.dto;

import com.ecommerce.model.Purchase;
import com.ecommerce.utils.Role;

import java.util.List;

public record UserDTO(
        Long id,
        String username,
        String firstName,
        String lastName,
        String email,
        String phone,
        Role role,
        List<Purchase> purchase

) {}
