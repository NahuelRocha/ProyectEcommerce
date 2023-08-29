package com.ecommerce.service;

import com.ecommerce.dto.OrderDetailDTO;
import com.ecommerce.dto.OrderDetailRequest;
import com.ecommerce.dto.UpdateOrderDetailRequest;
import com.ecommerce.model.OrderDetail;
import com.ecommerce.model.Purchase;

import java.util.List;

public interface OrderDetailService {

    OrderDetailDTO createOrderDetail(OrderDetailRequest orderDetailRequest);

    String deleteOrderDetail(Long id);

    OrderDetail updateOderDetail(UpdateOrderDetailRequest updateOrder);

    List<OrderDetail> findByPurchase(Long purchaseId);


}
