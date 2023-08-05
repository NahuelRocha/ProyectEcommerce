package com.ecommerce.utils;

import com.ecommerce.model.OrderDetail;

import java.util.List;

public class PurchaseUtils {

    public static Double calculateTotalPrice(List<OrderDetail> orderDetails) {

        Double updatePrice = 0.0;

        for (OrderDetail order : orderDetails){

            Double totalPrice = order.getTotalPrice();

            updatePrice += totalPrice;

        }

        return updatePrice;
    }

    public static Integer calculateQuantityOfProduct(List<OrderDetail> orderDetails){

        Integer quantity = 0;

        for (OrderDetail order : orderDetails){

            Integer getQuantity = order.getDetailQuantity();

            quantity += getQuantity;

        }
        return quantity;
    }

}
