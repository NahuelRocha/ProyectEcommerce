package com.ecommerce.controller;

import com.ecommerce.dto.CategoryDTO;
import com.ecommerce.dto.UserDTO;
import com.ecommerce.model.Category;
import com.ecommerce.service.impl.CategoryServiceImpl;
import jakarta.validation.Valid;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping( "/api/category" )
@RequiredArgsConstructor
public class CategoryController {


    private final CategoryServiceImpl categoryService;

    @PostMapping( "/create" )
    public ResponseEntity<CategoryDTO> createCategory( @Valid @RequestBody Category category) {
        CategoryDTO currentCategory = categoryService.createCategory( category );
        return new ResponseEntity<>( currentCategory, HttpStatus.CREATED );
    }

    @GetMapping( "/get-all" )
    public ResponseEntity<List<CategoryDTO>> getAllCategories() {
        List<CategoryDTO> categoryList = categoryService.getAllCategories();
        return ResponseEntity.ok( categoryList );
    }

    @GetMapping("/{id}")
    public ResponseEntity<CategoryDTO> getCategoryById(@PathVariable( "id" ) Long id ) {
        CategoryDTO categoryDTO = categoryService.getCategory( id ) ;
        return ResponseEntity.ok( categoryDTO );
    }
}
