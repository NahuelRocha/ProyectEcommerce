package com.ecommerce.dto;

import com.ecommerce.model.OrderDetail;
import com.ecommerce.utils.OrderStatus;

import java.time.LocalDateTime;
import java.util.List;


public record PurchaseDTO(Long id, LocalDateTime date,
                          String username, String orderId, OrderStatus orderStatus, List<OrderDetail> orderDetail) {

}
