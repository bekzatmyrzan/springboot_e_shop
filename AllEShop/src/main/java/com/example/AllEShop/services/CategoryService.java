package com.example.AllEShop.services;

import com.example.AllEShop.entities.Category;

import java.util.List;

public interface CategoryService {
    Category addCategory(Category category);
    List<Category> getAllCategories();
    Category getCategory(Long id);
    Category getCategoryByName(String name);
    void deleteCategory(Category category);
    void deleteCategoryById(Long id);
    Category saveCategory(Category category);
}
