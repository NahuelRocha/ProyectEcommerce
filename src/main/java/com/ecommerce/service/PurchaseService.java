package com.ecommerce.service;

import com.ecommerce.dto.PurchaseDTO;
import com.ecommerce.dto.PurchaseRequestDTO;


public interface PurchaseService {

    PurchaseDTO createPurchase(PurchaseRequestDTO purchaseRequestDTO);



}
