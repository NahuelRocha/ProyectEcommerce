package com.ecommerce.service;

import com.ecommerce.dto.ProductDTO;
import com.ecommerce.dto.ProductRequestDTO;
import com.ecommerce.model.Product;

import java.util.List;

public interface ProductService {

    ProductDTO createProduct(ProductRequestDTO productRequestDTO);

    ProductDTO getProduct(Long id);

    ProductDTO updateProduct(Long id , Product product);

    String deleteProduct(Long id);

    List<ProductDTO> getAllProducts();

}
