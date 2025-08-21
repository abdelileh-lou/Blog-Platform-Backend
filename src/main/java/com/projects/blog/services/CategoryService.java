package com.projects.blog.services;

import com.projects.blog.dtos.CategoryDTO;
import com.projects.blog.entities.Category;

import java.util.List;
import java.util.UUID;

public interface CategoryService {
    List<Category> listCategories();
    Category saveCategory(Category category);
    void deleteCategory(UUID id);
    Category getCategoryById(UUID id);
}
