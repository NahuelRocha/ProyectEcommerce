package com.ecommerce.dto;


import com.ecommerce.model.OrderDetail;

import java.time.LocalDate;
import java.util.List;


public record PurchaseDTO(Long id, LocalDate date, Double totalPrice, String username,
                          List<OrderDetail> orderDetails) {

}
