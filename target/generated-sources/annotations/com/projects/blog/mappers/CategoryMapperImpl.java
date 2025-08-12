package com.projects.blog.mappers;

import com.projects.blog.dtos.CategoryDTO;
import com.projects.blog.dtos.CreateCategoryRequest;
import com.projects.blog.entities.Category;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-08-12T17:40:39+0100",
    comments = "version: 1.6.3, compiler: javac, environment: Java 21.0.7 (Microsoft)"
)
@Component
public class CategoryMapperImpl implements CategoryMapper {

    @Override
    public CategoryDTO toDto(Category category) {
        if ( category == null ) {
            return null;
        }

        CategoryDTO.CategoryDTOBuilder categoryDTO = CategoryDTO.builder();

        categoryDTO.postCount( calculatePostCount( category.getPosts() ) );
        categoryDTO.id( category.getId() );
        categoryDTO.name( category.getName() );

        return categoryDTO.build();
    }

    @Override
    public Category toEntity(CreateCategoryRequest createCategoryRequest) {
        if ( createCategoryRequest == null ) {
            return null;
        }

        Category.CategoryBuilder category = Category.builder();

        category.name( createCategoryRequest.getName() );

        return category.build();
    }
}
