package com.ecommerce.service.impl;

import com.ecommerce.dto.OrderDetailDTO;
import com.ecommerce.dto.OrderDetailRequest;
import com.ecommerce.exception.InsufficientStockException;
import com.ecommerce.exception.ResourceNotFoundException;
import com.ecommerce.mapper.Mappers;
import com.ecommerce.model.OrderDetail;
import com.ecommerce.model.Product;
import com.ecommerce.model.Purchase;
import com.ecommerce.repository.OrderDetailRepository;
import com.ecommerce.repository.ProductRepository;
import com.ecommerce.repository.PurchaseRepository;
import com.ecommerce.service.OrderDetailService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class OrderDetailServiceImpl implements OrderDetailService {

    private final OrderDetailRepository orderDetailRepository;
    private final ProductRepository productRepository;
    private final PurchaseRepository purchaseRepository;


    public OrderDetailDTO createOrderDetail(OrderDetailRequest orderDetailRequest) {

        Product findProduct = productRepository.findById(orderDetailRequest.productId())
                .orElseThrow(() -> new ResourceNotFoundException("Product not found with ID: " + orderDetailRequest.productId()));

        Purchase findPurchase = purchaseRepository.findById(orderDetailRequest.purchaseId())
                .orElseThrow(() -> new ResourceNotFoundException("Purchase not found with ID: " + orderDetailRequest.purchaseId()));


        if (findProduct.getStock() < orderDetailRequest.detailQuantity()) {

            throw new InsufficientStockException("Insufficient stock");

        }

        OrderDetail newOrderDetail = new OrderDetail();
        newOrderDetail.setOrderId(UUID.randomUUID().toString());
        newOrderDetail.setDetailPrice(findProduct.getPrice());
        newOrderDetail.setDetailQuantity(orderDetailRequest.detailQuantity());
        newOrderDetail.setTotalPrice(findProduct.getPrice() * orderDetailRequest.detailQuantity());
        newOrderDetail.setPurchase(findPurchase);
        newOrderDetail.setProduct(findProduct);

        findProduct.setStock(findProduct.getStock() - orderDetailRequest.detailQuantity());

        findPurchase.getOrderDetail().add(newOrderDetail);

        orderDetailRepository.save(newOrderDetail);

        purchaseRepository.save(findPurchase);


        return Mappers.orderDetailToDTO(newOrderDetail, findProduct ,findPurchase.getUserName());


    }
}
