package com.ecommerce.service;

import com.ecommerce.dto.ProductDTO;
import com.ecommerce.dto.ProductRequestDTO;

public interface ProductService {

    ProductDTO createProduct(ProductRequestDTO productRequestDTO);

}
