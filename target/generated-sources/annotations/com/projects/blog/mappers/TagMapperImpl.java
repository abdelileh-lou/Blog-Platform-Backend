package com.projects.blog.mappers;

import com.projects.blog.dtos.TagResponse;
import com.projects.blog.entities.Tag;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-08-21T12:39:31+0100",
    comments = "version: 1.6.3, compiler: javac, environment: Java 21.0.7 (Microsoft)"
)
@Component
public class TagMapperImpl implements TagMapper {

    @Override
    public TagResponse toTagResponse(Tag tag) {
        if ( tag == null ) {
            return null;
        }

        TagResponse.TagResponseBuilder tagResponse = TagResponse.builder();

        if ( tag.getPosts() != null ) {
            tagResponse.postCount( (int) calculatePostCount( tag.getPosts() ) );
        }
        tagResponse.id( tag.getId() );
        tagResponse.name( tag.getName() );

        return tagResponse.build();
    }

    @Override
    public Tag toEntity(TagResponse tagResponse) {
        if ( tagResponse == null ) {
            return null;
        }

        Tag.TagBuilder tag = Tag.builder();

        tag.id( tagResponse.getId() );
        tag.name( tagResponse.getName() );

        return tag.build();
    }
}
