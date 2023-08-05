package com.ecommerce.service;

import com.ecommerce.dto.OrderDetailDTO;
import com.ecommerce.dto.OrderDetailRequest;

public interface OrderDetailService {

    OrderDetailDTO createOrderDetail(OrderDetailRequest orderDetailRequest);

}
