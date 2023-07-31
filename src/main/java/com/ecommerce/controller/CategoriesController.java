package com.ecommerce.controller;

import com.ecommerce.model.Categories;
import com.ecommerce.service.impl.CategorieServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class CategoriesController {

    @Autowired
    private CategorieServiceImpl categorieServiceImpl;

    @GetMapping("/all")
    public List<Categories> getCategories(){
        return categorieServiceImpl.getCategories();
    }

    @GetMapping("get-one/{id}")
    public Optional<Categories> getOneCategory(@PathVariable Long id){
        return categorieServiceImpl.getOneCategory(id);
    }

    @PostMapping("/add-cat")
    public Categories addCategory(@RequestBody Categories cat){
        return categorieServiceImpl.saveCategory(cat);
    }

    @PutMapping("/update/{id}")
    public Categories updateCategory(@PathVariable Long id, @RequestBody Categories cat){
        return categorieServiceImpl.update(cat, id);
    }

    @DeleteMapping("/delete/{id}")
    public String deleteCategory(@PathVariable Long id){
        boolean isDeleted = categorieServiceImpl.deleteCategories((id));
        return isDeleted? "Category with number id "+id + "has been deleted": "It is not deleted, maybe it is used by a product";
    }
}
