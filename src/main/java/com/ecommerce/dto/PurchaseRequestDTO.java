package com.ecommerce.dto;

import com.ecommerce.model.OrderDetail;

import java.util.List;

public record PurchaseRequestDTO(
        Long idUser,
        Double price,
        Integer quantity,
        List<OrderDetail> orderDetails
) {}

