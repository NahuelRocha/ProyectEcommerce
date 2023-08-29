package com.ecommerce.repository;

import com.ecommerce.model.OrderDetail;
import com.ecommerce.model.Purchase;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface OrderDetailRepository extends JpaRepository<OrderDetail, Long> {

    List<OrderDetail> findByPurchase(Purchase purchase);
}
