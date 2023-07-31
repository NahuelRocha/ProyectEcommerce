package com.ecommerce.service;

import com.ecommerce.model.Categories;

import java.util.List;
import java.util.Optional;

public interface CategorieService {

    public List<Categories> getCategories();
    public Optional<Categories> getOneCategory(Long id);
    public Categories saveCategory(Categories cat);

    public Categories update(Categories request, Long id);
    public boolean deleteCategories(Long id);
}
