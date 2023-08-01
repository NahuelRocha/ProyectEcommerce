package com.ecommerce.service.impl;

import com.ecommerce.dto.CategoryDTO;
import com.ecommerce.dto.UserDTO;
import com.ecommerce.exception.ResourceNotFoundException;
import com.ecommerce.mapper.Mappers;
import com.ecommerce.model.Category;
import com.ecommerce.model.User;
import com.ecommerce.repository.CategoryRepository;
import com.ecommerce.service.CategoryService;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {

    @NonNull
    private CategoryRepository categoryRepository;

    @Override
    public CategoryDTO createCategory(Category category) {

        Category placeholderCategory = new Category();

        placeholderCategory.setId( category.getId() );
        placeholderCategory.setName( category.getName() );
        placeholderCategory.setDescription( category.getDescription() );

        categoryRepository.save( placeholderCategory );

        return Mappers.categoryToCategoryDTO( placeholderCategory );
    }

    @Override
    public List<CategoryDTO> getAllCategories() {
        return categoryRepository
                .findAll().stream()
                .map( Mappers::categoryToCategoryDTO ).collect( Collectors.toList() );
    }


    @Override
    public CategoryDTO getCategory(Long id) {
        Category category = categoryRepository.findById( id )
                .orElseThrow( () -> new ResourceNotFoundException( "Category not found with ID: " + id ) )                ;
        return Mappers.categoryToCategoryDTO( category );
    }

    @Override
    public CategoryDTO updateCategory(Long id) {
        return null;
    }

    @Override
    public String deleteCategory(Long id) {
        return null;
    }
}
