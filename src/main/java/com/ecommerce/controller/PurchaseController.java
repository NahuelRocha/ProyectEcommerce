package com.ecommerce.controller;

import com.ecommerce.dto.PurchaseDTO;
import com.ecommerce.dto.PurchaseRequestDTO;
import com.ecommerce.service.PurchaseService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/purchase")
@RequiredArgsConstructor
public class PurchaseController {

    private final PurchaseService purchaseService;

    @PostMapping("/create/{id}")
    public ResponseEntity<PurchaseDTO> createPurchase(@Valid @PathVariable("id")
                                                          PurchaseRequestDTO purchaseRequestDTO) {

        PurchaseDTO newPurchase = purchaseService.createPurchase(purchaseRequestDTO);

        return new ResponseEntity<>(newPurchase, HttpStatus.CREATED);

    }


}
