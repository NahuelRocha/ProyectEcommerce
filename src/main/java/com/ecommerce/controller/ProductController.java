package com.ecommerce.controller;

import com.ecommerce.dto.ProductDTO;
import com.ecommerce.dto.ProductRequestDTO;
import com.ecommerce.model.Product;
import com.ecommerce.service.ProductService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @GetMapping("/{id}")
    private ResponseEntity<ProductDTO> getProduct(@PathVariable("id") Long id) {

        ProductDTO findProduct = productService.getProduct(id);

        return ResponseEntity.ok(findProduct);

    }

    @PutMapping("/{id}")
    private ResponseEntity<ProductDTO> updateProduct(@PathVariable("id") Long id,
                                                     @Valid @RequestBody Product updateProduct) {

        ProductDTO product = productService.updateProduct(id, updateProduct);

        return ResponseEntity.ok(product);

    }

    @DeleteMapping("/{id}")
    private ResponseEntity<String> deleteProduct(@PathVariable("id") Long id) {

        String delete = productService.deleteProduct(id);

        return ResponseEntity.ok(delete);

    }

    @GetMapping("/all-products")
    private ResponseEntity<List<ProductDTO>> getAllProducts() {

        List<ProductDTO> listOfProducts = productService.getAllProducts();

        return ResponseEntity.ok(listOfProducts);

    }


}
