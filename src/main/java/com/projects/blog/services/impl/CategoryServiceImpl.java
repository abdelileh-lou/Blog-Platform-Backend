package com.projects.blog.services.impl;

import com.projects.blog.dtos.CategoryDTO;
import com.projects.blog.entities.Category;
import com.projects.blog.repositories.CategoryRepository;
import com.projects.blog.services.CategoryService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;



    @Override
    public List<Category> listCategories() {
        return categoryRepository.findAllWithPostsCount();
    }

    @Override
    @Transactional
    public Category saveCategory(Category category) {
        if(categoryRepository.existsByNameIgnoreCase(category.getName())){
            throw new IllegalArgumentException("Category already exists with name " + category.getName());
        }
        return categoryRepository.save(category);
    }
}
