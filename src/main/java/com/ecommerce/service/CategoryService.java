package com.ecommerce.service;

import com.ecommerce.dto.CategoryDTO;
import com.ecommerce.model.Category;

import java.util.List;

public interface CategoryService {
    CategoryDTO createCategory( Category category );
    List<CategoryDTO> getAllCategories();
    CategoryDTO getCategory( Long id );
    CategoryDTO updateCategory( Long id );
    String deleteCategory( Long id );

}
