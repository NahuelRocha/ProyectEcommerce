package com.ecommerce.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public record OrderDetailRequest(
        @NotNull(message = "The user id is missing")
        Long productId,

        @NotNull(message = "The user id is missing")
        Long purchaseId,

        @Min(value = 1 , message = "Quantity must be at least 1")
        Integer detailQuantity
) {}
