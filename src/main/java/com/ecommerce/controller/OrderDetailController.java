package com.ecommerce.controller;

import com.ecommerce.dto.OrderDetailDTO;
import com.ecommerce.dto.OrderDetailRequest;
import com.ecommerce.dto.UpdateOrderDetailRequest;
import com.ecommerce.model.OrderDetail;
import com.ecommerce.service.OrderDetailService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @DeleteMapping("/{id}")
    private ResponseEntity<String> deleteOrderDetail(@PathVariable("id") Long id){

        String deleteOrder = orderDetailService.deleteOrderDetail(id);

        return ResponseEntity.ok(deleteOrder);

    }

    @PutMapping("/update")
    private ResponseEntity<OrderDetail> updateOrderDetail(@Valid @RequestBody UpdateOrderDetailRequest update){

        OrderDetail updateOrder = orderDetailService.updateOderDetail(update);

        return ResponseEntity.ok(updateOrder);

    }

}
