package com.ecommerce.dto;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record PurchaseRequestDTO(
        @NotNull(message = "The user id is missing")
        Long idUser,

        @NotBlank(message = "Enter the address")
        String shippingAddress
) {}

