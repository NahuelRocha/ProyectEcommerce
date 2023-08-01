package com.ecommerce.service.impl;

import com.ecommerce.dto.PurchaseDTO;
import com.ecommerce.dto.PurchaseRequestDTO;
import com.ecommerce.exception.ResourceNotFoundException;
import com.ecommerce.mapper.Mappers;
import com.ecommerce.model.OrderDetail;
import com.ecommerce.model.Purchase;
import com.ecommerce.model.User;
import com.ecommerce.repository.PurchaseRepository;
import com.ecommerce.repository.UserRepository;
import com.ecommerce.service.PurchaseService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PurchaseServiceImpl implements PurchaseService {

    private final PurchaseRepository purchaseRepository;
    private final UserRepository userRepository;

    @Override
    public PurchaseDTO createPurchase(PurchaseRequestDTO purchaseRequestDTO) {

        User user = userRepository.findById(purchaseRequestDTO.idUser())
                .orElseThrow(() -> new ResourceNotFoundException("User not found with ID: "));

        Purchase newPurchase = new Purchase();
        newPurchase.setDate(LocalDate.now());
        newPurchase.setTotalPrice(purchaseRequestDTO.price());
        newPurchase.setQuantity(purchaseRequestDTO.quantity());
        newPurchase.setUserName(user.getUsername());
        newPurchase.setOrderDetails(purchaseRequestDTO.orderDetails());

        purchaseRepository.save(newPurchase);

        return Mappers.purchaseToPurchaseDTO(newPurchase);
    }

}
