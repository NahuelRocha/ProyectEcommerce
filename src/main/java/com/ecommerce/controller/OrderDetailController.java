package com.ecommerce.controller;

import com.ecommerce.dto.OrderDetailDTO;
import com.ecommerce.dto.OrderDetailRequest;
import com.ecommerce.service.OrderDetailService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/order-detail")
@RequiredArgsConstructor
public class OrderDetailController {

    private final OrderDetailService orderDetailService;

    @PostMapping("/create")
    private ResponseEntity<OrderDetailDTO> createOrderDetail(@Valid @RequestBody OrderDetailRequest orderDetailRequest) {

        OrderDetailDTO newOrderDetail = orderDetailService.createOrderDetail(orderDetailRequest);

        return new ResponseEntity<>(newOrderDetail, HttpStatus.CREATED);

    }

}
