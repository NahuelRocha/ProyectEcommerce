package com.ecommerce.service;

import com.ecommerce.dto.OrderDetailDTO;
import com.ecommerce.dto.OrderDetailRequest;
import com.ecommerce.dto.UpdateOrderDetailRequest;
import com.ecommerce.model.OrderDetail;

public interface OrderDetailService {

    OrderDetailDTO createOrderDetail(OrderDetailRequest orderDetailRequest);

    String deleteOrderDetail(Long id);

    OrderDetail updateOderDetail(UpdateOrderDetailRequest updateOrder);


}
