package com.ecommerce.dto;

import com.ecommerce.model.OrderDetails;

import java.time.LocalDate;


public record PurchaseDTO(Long id, LocalDate date, Double totalPrice, String username,
                          OrderDetails orderDetails) {

}
