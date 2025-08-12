package com.projects.blog.mappers;


import com.projects.blog.dtos.CategoryDTO;
import com.projects.blog.dtos.CreateCategoryRequest;
import com.projects.blog.entities.Category;
import com.projects.blog.entities.Post;
import com.projects.blog.enums.PostStatus;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring" , unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface CategoryMapper {

    @Mapping(target = "postCount", source ="posts", qualifiedByName = "calculatePostCount")
    CategoryDTO toDto(Category category);

    Category toEntity(CreateCategoryRequest createCategoryRequest);

    @Named( "calculatePostCount")
    default long calculatePostCount(List<Post> posts){
        if(null == posts){
            return 0;
        }
        return posts.stream().filter(post -> PostStatus.PUBLISHED.equals(post.getStatus())).count();
    }


}
