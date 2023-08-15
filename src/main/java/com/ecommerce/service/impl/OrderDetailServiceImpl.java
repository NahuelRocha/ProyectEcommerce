package com.ecommerce.service.impl;

import com.ecommerce.dto.OrderDetailDTO;
import com.ecommerce.dto.OrderDetailRequest;
import com.ecommerce.dto.UpdateOrderDetailRequest;
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


        return Mappers.orderDetailToDTO(newOrderDetail, findProduct, findPurchase.getUserName());


    }

    @Override
    public String deleteOrderDetail(Long id) {

        OrderDetail findOrder = orderDetailRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Order not found with ID: " + id));

        orderDetailRepository.delete(findOrder);

        return "Order detail successfully removed";

    }

    @Override
    public OrderDetail updateOderDetail(UpdateOrderDetailRequest updateOrder) {

        OrderDetail existingOrder = orderDetailRepository.findById(updateOrder.idOrder())
                .orElseThrow(() -> new ResourceNotFoundException("Order detail not found with ID: " + updateOrder.idOrder()));

        Long idProduct = existingOrder.getProduct().getId();

        Integer currentAmount = existingOrder.getDetailQuantity();

        Product findProduct = productRepository.findById(idProduct)
                .orElseThrow(() -> new ResourceNotFoundException("Product not found with ID: " + idProduct));


        if (updateOrder.quantity() > currentAmount) {

            Integer addQuantity = updateOrder.quantity() - currentAmount;

            if (findProduct.getStock() < addQuantity) {

                throw new InsufficientStockException("Insufficient stock");

            } else {
                existingOrder.setDetailQuantity(updateOrder.quantity());

                existingOrder.setTotalPrice(findProduct.getPrice() * updateOrder.quantity());

                findProduct.setStock(findProduct.getStock() - addQuantity);
            }

        } else {

            Integer restQuantity = currentAmount - updateOrder.quantity();

            existingOrder.setDetailQuantity(updateOrder.quantity());

            existingOrder.setTotalPrice(findProduct.getPrice() * updateOrder.quantity());

            findProduct.setStock(findProduct.getStock() + restQuantity);

        }

        orderDetailRepository.save(existingOrder);

        productRepository.save(findProduct);

        return existingOrder;

    }
}
