package com.ecommerce.service.impl;

import com.ecommerce.dto.ProductDTO;
import com.ecommerce.dto.ProductRequestDTO;
import com.ecommerce.exception.ResourceNotFoundException;
import com.ecommerce.mapper.Mappers;
import com.ecommerce.model.Product;
import com.ecommerce.repository.OrderDetailRepository;
import com.ecommerce.repository.ProductRepository;
import com.ecommerce.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final OrderDetailRepository orderDetailRepository;


    public ProductDTO createProduct(ProductRequestDTO productRequestDTO) {

        Optional<Product> existingProduct = productRepository.findByName(productRequestDTO.name());

        if (existingProduct.isEmpty()) {
            Product newProduct = new Product();
            newProduct.setName(productRequestDTO.name());
            newProduct.setStock(productRequestDTO.stock());
            newProduct.setDescription(productRequestDTO.description());
            newProduct.setPrice(productRequestDTO.price());
            productRepository.save(newProduct);
            return Mappers.mapProductToDTO(newProduct);

        } else {
            throw new IllegalArgumentException("The product already exists in the database");
        }

    }


    public ProductDTO getProduct(Long id) {

        Product findProduct = productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Product not found with ID: " + id));

        return Mappers.mapProductToDTO(findProduct);

    }


    public ProductDTO updateProduct(Long id, Product product) {

        Product findProduct = productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Product not found with ID: " + id));

        findProduct.setName(product.getName());
        findProduct.setStock(product.getStock());
        findProduct.setDescription(product.getDescription());
        findProduct.setPrice(product.getPrice());

        productRepository.save(findProduct);

        return Mappers.mapProductToDTO(findProduct);

    }


    public String deleteProduct(Long id) {

        Product findProduct = productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Product not found with ID: " + id));

        boolean isPresent = orderDetailRepository.findAll().stream().anyMatch(order ->
                order.getProduct().getId().equals(findProduct.getId()));

        if (isPresent) {
            throw new ResourceNotFoundException("Product cannot be removed as it has outstanding order details");
        }

        productRepository.delete(findProduct);

        return "Product removed with success";

    }


    public List<ProductDTO> getAllProducts() {

        List<Product> productList = productRepository.findAll();

        if (productList.isEmpty()) {
            throw new ResourceNotFoundException("No objects created in the database");
        } else {
            return productList.stream().map(Mappers::mapProductToDTO).collect(Collectors.toList());
        }

    }


}
