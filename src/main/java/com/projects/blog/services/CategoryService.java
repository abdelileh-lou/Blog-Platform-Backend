package com.projects.blog.services;

import com.projects.blog.dtos.CategoryDTO;
import com.projects.blog.entities.Category;

import java.util.List;

public interface CategoryService {
    List<Category> listCategories();
;
}
