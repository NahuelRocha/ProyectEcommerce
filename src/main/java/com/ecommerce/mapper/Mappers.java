package com.ecommerce.mapper;


import com.ecommerce.dto.*;
import com.ecommerce.model.*;
import com.ecommerce.utils.OrderStatus;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.UUID;

public class Mappers {


    public static UserDTO userToUserDTO(User user) {

        return new UserDTO(
                user.getId(),
                user.getUsername(),
                user.getFirstName(),
                user.getLastName(),
                user.getEmail(),
                user.getPhone(),
                user.getRole(),
                user.getPurchase()
        );
    }

    public static void mapUser(User oldUser, User updateUser) {

        oldUser.setUsername(updateUser.getUsername());
        oldUser.setFirstName(updateUser.getFirstName());
        oldUser.setLastName(updateUser.getLastName());
        oldUser.setEmail(updateUser.getEmail());
        oldUser.setPhone(updateUser.getPhone());
        oldUser.setRole(updateUser.getRole());
        oldUser.setPurchase(updateUser.getPurchase());

    }


    public static PurchaseDTO purchaseToPurchaseDTO(Purchase purchase){

        return new PurchaseDTO(
                purchase.getId(),
                purchase.getDate(),
                purchase.getUserName(),
                purchase.getOrderId(),
                purchase.getOrderStatus(),
                purchase.getOrderDetail()
        );

    }

        public static Purchase mapPurchase(PurchaseRequestDTO purchaseRequestDTO , User user) {

            Purchase newPurchase = new Purchase();
            newPurchase.setOrderId(UUID.randomUUID().toString());
            newPurchase.setDate(LocalDateTime.now());
            newPurchase.setOrderStatus(OrderStatus.PLACED);
            newPurchase.setShippingAddress(purchaseRequestDTO.shippingAddress());
            newPurchase.setUserName(user.getUsername());
            newPurchase.setUser(user);

            return newPurchase;


        }


    public static CategoryDTO categoryToCategoryDTO( Category category ) {
        return new CategoryDTO(
                category.getId(),
                category.getName(),
                category.getDescription()
        );
    }

    public static OrderDetailDTO orderDetailToDTO (OrderDetail orderDetail , Product product , String username){

        ProductDTO newProductDTO = new ProductDTO(
                product.getId(),
                product.getName(),
                product.getStock(),
                product.getDescription(),
                product.getPrice()
        );

        return new OrderDetailDTO(
                orderDetail.getId(),
                orderDetail.getOrderId(),
                orderDetail.getDetailPrice(),
                orderDetail.getDetailQuantity(),
                orderDetail.getTotalPrice(),
                newProductDTO,
                username
        );

    }

    public static ProductDTO mapProductToDTO(Product product){

        return new ProductDTO(
                product.getId(),
                product.getName(),
                product.getStock(),
                product.getDescription(),
                product.getPrice()
        );

    }


}
