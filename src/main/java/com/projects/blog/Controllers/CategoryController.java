package com.projects.blog.Controllers;



import com.projects.blog.dtos.CategoryDTO;
import com.projects.blog.dtos.CreateCategoryRequest;
import com.projects.blog.entities.Category;
import com.projects.blog.mappers.CategoryMapper;
import com.projects.blog.services.CategoryService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/categories")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class CategoryController {

    private final CategoryService categoryService;
    private final CategoryMapper categoryMapper;

    @GetMapping
    public ResponseEntity<List<CategoryDTO>> listCategories() {
        List<CategoryDTO> categories = categoryService.listCategories().stream().map(category -> categoryMapper.toDto(category)).toList();
        return ResponseEntity.ok(categories);
    }

    @PostMapping
    public ResponseEntity<CategoryDTO> createCategory(@Valid @RequestBody CreateCategoryRequest request) {
      Category category =  categoryMapper.toEntity(request);
      Category saveCategory = categoryService.saveCategory(category) ;
      return new ResponseEntity<>(
              categoryMapper.toDto(saveCategory),
              HttpStatus.CREATED
      );
    }
}
