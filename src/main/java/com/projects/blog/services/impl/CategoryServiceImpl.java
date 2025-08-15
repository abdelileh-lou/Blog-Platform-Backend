package com.projects.blog.services.impl;

import com.projects.blog.dtos.CategoryDTO;
import com.projects.blog.entities.Category;
import com.projects.blog.repositories.CategoryRepository;
import com.projects.blog.services.CategoryService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;


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

    @Override
    public void deleteCategory(UUID id) {
       Optional<Category> category =  categoryRepository.findById(id);
       if (category.isPresent()){
           if (category.get().getPosts().size() > 0){
               throw new IllegalStateException("Category has posts associated with it. Cannot be deleted");
           }

           categoryRepository.deleteById(id);
       }
    }
}
