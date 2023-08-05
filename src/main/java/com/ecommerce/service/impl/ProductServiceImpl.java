package com.ecommerce.service.impl;

import com.ecommerce.dto.ProductDTO;
import com.ecommerce.dto.ProductRequestDTO;
import com.ecommerce.mapper.Mappers;
import com.ecommerce.model.Product;
import com.ecommerce.repository.ProductRepository;
import com.ecommerce.service.ProductService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;


    public ProductDTO createProduct(ProductRequestDTO productRequestDTO) {

        Optional<Product> existingProduct = productRepository.findByName(productRequestDTO.name());

        if(existingProduct.isEmpty()){
        Product newProduct = new Product();
        newProduct.setName(productRequestDTO.name());
        newProduct.setStock(productRequestDTO.stock());
        newProduct.setDescription(productRequestDTO.description());
        newProduct.setPrice(productRequestDTO.price());
        productRepository.save(newProduct);
        return Mappers.mapProductToDTO(newProduct);

        } else throw new IllegalArgumentException("The product already exists in the database");

    }


}
