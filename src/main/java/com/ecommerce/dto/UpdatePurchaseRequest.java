package com.ecommerce.dto;


import jakarta.validation.constraints.*;

public record UpdatePurchaseRequest(

        @Min(value = 1, message = "Min 1")
        @NotNull(message = "Purchase id cannot be null")
        Long idPurchase,


        @Pattern(regexp = "PLACED|SHIPPED|DELIVERED|CANCELLED", message = "Invalid order status")
        @NotBlank(message = "Enter the order status")
        String orderStatus,

        @NotBlank(message = "Enter the address")
        String shippingAddress


) {
}
