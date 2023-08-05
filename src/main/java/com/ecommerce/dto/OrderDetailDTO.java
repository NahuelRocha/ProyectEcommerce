package com.ecommerce.dto;

public record OrderDetailDTO(
        Long id,
        String orderId,
        Double detailPrice,
        Integer detailQuantity,
        Double totalPrice,
        ProductDTO productDTO,
        String username
) {}
