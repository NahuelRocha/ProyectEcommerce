package com.ecommerce.controller;

import com.ecommerce.dto.PurchaseDTO;
import com.ecommerce.model.Purchase;
import com.ecommerce.service.PurchaseService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/purchase")
@RequiredArgsConstructor
public class PurchaseController {

    private final PurchaseService purchaseService;

    @PostMapping("/create")
    public ResponseEntity<PurchaseDTO> createPurchase(@Valid @RequestBody Purchase purchase){

        PurchaseDTO newPurchase = purchaseService.createPurchase(purchase);

        return new ResponseEntity<>(newPurchase , HttpStatus.CREATED);

    }


}
