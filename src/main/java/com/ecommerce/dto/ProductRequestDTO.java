package com.ecommerce.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record ProductRequestDTO(

        @NotBlank(message = "The name of the product is missing")
        String name,

        @Min(value = 0, message = "Stock cannot be less than zero")
        Integer stock,

        @NotBlank(message = "The product description is missing")
        String description,

        @NotNull(message = "This field cannot be null")
        Double price
) {}
