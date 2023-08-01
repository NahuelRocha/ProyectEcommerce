package com.ecommerce.service.impl;

import com.ecommerce.dto.PurchaseDTO;
import com.ecommerce.exception.ResourceNotFoundException;
import com.ecommerce.mapper.Mappers;
import com.ecommerce.model.Purchase;
import com.ecommerce.model.User;
import com.ecommerce.repository.PurchaseRepository;
import com.ecommerce.repository.UserRepository;
import com.ecommerce.service.PurchaseService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PurchaseServiceImpl implements PurchaseService {

    private final PurchaseRepository purchaseRepository;
    private final UserRepository userRepository;

    @Override
    public PurchaseDTO createPurchase(Purchase purchase) {

        User user = userRepository.findById(purchase.getUser().getId())
                .orElseThrow(() -> new ResourceNotFoundException("User not found with ID: "));

        String username = user.getUsername();

        purchase.setUserName(username);

        purchaseRepository.save(purchase);

        return Mappers.purchaseToPurchaseDTO(purchase);
    }
}
