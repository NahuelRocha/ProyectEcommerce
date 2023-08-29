package com.ecommerce.controller;

import com.ecommerce.dto.PurchaseDTO;
import com.ecommerce.dto.PurchaseRequestDTO;
import com.ecommerce.dto.UpdatePurchaseRequest;
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

    @PostMapping("/create")
    public ResponseEntity<PurchaseDTO> createPurchase(@Valid @RequestBody
                                                      PurchaseRequestDTO purchaseRequestDTO) {

        PurchaseDTO newPurchase = purchaseService.createPurchase(purchaseRequestDTO);

        return new ResponseEntity<>(newPurchase, HttpStatus.CREATED);

    }

    @GetMapping("/{id}")
    public ResponseEntity<PurchaseDTO> getPurchaseById(@Valid @PathVariable("id") Long id) {

        PurchaseDTO purchase = purchaseService.getPurchaseById(id);

        return ResponseEntity.ok(purchase);

    }

    @GetMapping("/user/{username}")
    public ResponseEntity<List<PurchaseDTO>> findByUserName(@PathVariable("username") String username) {

        List<PurchaseDTO> findPurchases = purchaseService.findByUserName(username);

        return ResponseEntity.ok(findPurchases);

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletePurchase(@PathVariable("id") Long id) {

        String deleted = purchaseService.deletePurchase(id);

        return ResponseEntity.ok(deleted);

    }

    @PutMapping("/update")
    public ResponseEntity<String> updatePurchase(@Valid @RequestBody UpdatePurchaseRequest update) {

        String updatePurchase = purchaseService.updatePurchase(update);

        return ResponseEntity.ok(updatePurchase);
    }


}
