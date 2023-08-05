package com.ecommerce.controller;

import com.ecommerce.dto.ProductDTO;
import com.ecommerce.dto.ProductRequestDTO;
import com.ecommerce.service.ProductService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/product")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @PostMapping("/create")
    public ResponseEntity<ProductDTO> createProduct(@Valid @RequestBody ProductRequestDTO productRequestDTO) {

        ProductDTO newProduct = productService.createProduct(productRequestDTO);

        return new ResponseEntity<>(newProduct, HttpStatus.CREATED);

    }

}
