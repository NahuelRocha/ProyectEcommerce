package com.ecommerce.dto;



public record UserUpdateRequest(
        String firstName,
        String lastName,
        String email,
        String phone
) {
}
