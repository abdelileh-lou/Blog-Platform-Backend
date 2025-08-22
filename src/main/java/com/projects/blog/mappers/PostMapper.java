package com.projects.blog.mappers;


import com.projects.blog.dtos.*;
import com.projects.blog.entities.Post;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring" , unmappedTargetPolicy = ReportingPolicy.IGNORE)

public interface PostMapper {

    @Mapping(target = "author" ,source = "author")
    @Mapping(target = "tags", source ="tags")
    @Mapping(target = "category", source ="category")
    PostDto toPostDto(Post post);


    CreatePostRequest toCreatePostRequest(CreatePostRequestDto createPostRequestDto);

    UpdatePostRequest toUpdatePostRequest(UpdatePostRequestDto updatePostRequestDto);

}
