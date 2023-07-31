package com.ecommerce.service.impl;

import com.ecommerce.model.Categories;
import com.ecommerce.repository.CategoryRepository;
import com.ecommerce.service.CategorieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class CategorieServiceImpl implements CategorieService {

    @Autowired
    CategoryRepository categoryRepository;

    public List<Categories> getCategories(){
        return categoryRepository.findAll();
    }

    public Optional<Categories> getOneCategory(Long id){
        return categoryRepository.findById(id);
    }

    public Categories saveCategory(Categories cat){
        return categoryRepository.save(cat);
    }

    public Categories update(Categories request, Long id){
        Optional<Categories> optionalCategory=categoryRepository.findById(id);
        if(optionalCategory.isPresent()){
            Categories categoryUpdate = optionalCategory.get();
            categoryUpdate.setName(request.getName());

            categoryRepository.save(categoryUpdate);
            return categoryUpdate;
        }else{
            throw new IllegalArgumentException("<Category with ID " + id + " can't be found.");
        }
    }

    public boolean deleteCategories(Long id){
        try {
            categoryRepository.deleteById(id);
            return true;
        }catch (Exception e){
            return false;
        }
    }
}
