package com.ecommerce.service;

import com.ecommerce.dto.PurchaseDTO;
import com.ecommerce.dto.PurchaseRequestDTO;

import java.util.List;


public interface PurchaseService {

    PurchaseDTO createPurchase(PurchaseRequestDTO purchaseRequestDTO);

    PurchaseDTO getPurchaseById(Long id);

    List<PurchaseDTO> findByUserName(String userName);



}
