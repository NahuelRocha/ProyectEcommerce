package com.ecommerce.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public record UpdateOrderDetailRequest(
        @NotNull(message = "IdOrder cannot be null")
        Long idOrder,

        @Min(value = 1 , message = "Cannot be updated by {value}")
        Integer quantity
) {}
