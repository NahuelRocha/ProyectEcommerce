package com.ecommerce.service;

import com.ecommerce.dto.PurchaseDTO;
import com.ecommerce.dto.PurchaseRequestDTO;
import com.ecommerce.dto.UpdatePurchaseRequest;
import com.ecommerce.utils.OrderStatus;

import java.util.List;


public interface PurchaseService {

    PurchaseDTO createPurchase(PurchaseRequestDTO purchaseRequestDTO);

    PurchaseDTO getPurchaseById(Long id);

    List<PurchaseDTO> findByUserName(String userName);

    String deletePurchase(Long id);

    String updatePurchase(UpdatePurchaseRequest update);


}
