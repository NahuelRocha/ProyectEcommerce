package com.ecommerce.service.impl;

import com.ecommerce.dto.PurchaseDTO;
import com.ecommerce.dto.PurchaseRequestDTO;
import com.ecommerce.exception.ResourceNotFoundException;
import com.ecommerce.mapper.Mappers;
import com.ecommerce.model.Purchase;
import com.ecommerce.model.User;
import com.ecommerce.repository.PurchaseRepository;
import com.ecommerce.repository.UserRepository;
import com.ecommerce.service.PurchaseService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class PurchaseServiceImpl implements PurchaseService {

    private final PurchaseRepository purchaseRepository;
    private final UserRepository userRepository;


    @Override
    public PurchaseDTO createPurchase(PurchaseRequestDTO purchaseRequestDTO) {

        User user = userRepository.findById(purchaseRequestDTO.idUser())
                .orElseThrow(() -> new ResourceNotFoundException("User not found with ID: " + purchaseRequestDTO.idUser()));

        Purchase newPurchase = Mappers.mapPurchase(purchaseRequestDTO, user);

        user.getPurchase().add(newPurchase);

        purchaseRepository.save(newPurchase);

        userRepository.save(user);


        return Mappers.purchaseToPurchaseDTO(newPurchase);
    }

    @Override
    public PurchaseDTO getPurchaseById(Long id) {

        Purchase findPurchase = purchaseRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Purchase not found with ID: " + id));

        return Mappers.purchaseToPurchaseDTO(findPurchase);

    }

    @Override
    public List<PurchaseDTO> findByUserName(String userName) {

        List<Purchase> findPurchase = purchaseRepository.findByUserName(userName);

        if (findPurchase.isEmpty()) {
            throw new ResourceNotFoundException("No purchases were found associated with the user :" + userName);
        }

        return findPurchase.stream().map(Mappers::purchaseToPurchaseDTO)
                .collect(Collectors.toList());

    }

}
