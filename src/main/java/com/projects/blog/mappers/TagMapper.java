package com.projects.blog.mappers;


import com.projects.blog.dtos.TagResponse;
import com.projects.blog.entities.Post;
import com.projects.blog.entities.Tag;
import com.projects.blog.enums.PostStatus;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring" , unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface TagMapper {

    @Mapping(target = "postCount", source ="posts", qualifiedByName = "calculatePostCount")
    TagResponse toTagResponse(Tag tag);

    Tag toEntity(TagResponse tagResponse);

    @Named( "calculatePostCount")
    default long calculatePostCount(List<Post> posts){
        if(null == posts){
            return 0;
        }
        return (int) posts.stream().filter(post -> PostStatus.PUBLISHED.equals(post.getStatus())).count();
    }


}
