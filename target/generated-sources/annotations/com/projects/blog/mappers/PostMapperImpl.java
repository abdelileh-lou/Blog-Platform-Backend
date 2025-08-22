package com.projects.blog.mappers;

import com.projects.blog.dtos.AuthorDto;
import com.projects.blog.dtos.CategoryDTO;
import com.projects.blog.dtos.CreatePostRequest;
import com.projects.blog.dtos.CreatePostRequestDto;
import com.projects.blog.dtos.PostDto;
import com.projects.blog.dtos.TagResponse;
import com.projects.blog.dtos.UpdatePostRequest;
import com.projects.blog.dtos.UpdatePostRequestDto;
import com.projects.blog.entities.Category;
import com.projects.blog.entities.Post;
import com.projects.blog.entities.Tag;
import com.projects.blog.entities.User;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.UUID;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-08-22T18:08:12+0100",
    comments = "version: 1.6.3, compiler: javac, environment: Java 21.0.7 (Microsoft)"
)
@Component
public class PostMapperImpl implements PostMapper {

    @Override
    public PostDto toPostDto(Post post) {
        if ( post == null ) {
            return null;
        }

        PostDto.PostDtoBuilder postDto = PostDto.builder();

        postDto.author( userToAuthorDto( post.getAuthor() ) );
        postDto.tags( tagSetToTagResponseSet( post.getTags() ) );
        postDto.category( categoryToCategoryDTO( post.getCategory() ) );
        postDto.id( post.getId() );
        postDto.title( post.getTitle() );
        postDto.content( post.getContent() );
        postDto.readingTime( post.getReadingTime() );
        postDto.status( post.getStatus() );
        postDto.createdAt( post.getCreatedAt() );
        postDto.updatedAt( post.getUpdatedAt() );

        return postDto.build();
    }

    @Override
    public CreatePostRequest toCreatePostRequest(CreatePostRequestDto createPostRequestDto) {
        if ( createPostRequestDto == null ) {
            return null;
        }

        CreatePostRequest.CreatePostRequestBuilder createPostRequest = CreatePostRequest.builder();

        createPostRequest.title( createPostRequestDto.getTitle() );
        createPostRequest.content( createPostRequestDto.getContent() );
        createPostRequest.categoryId( createPostRequestDto.getCategoryId() );
        createPostRequest.status( createPostRequestDto.getStatus() );

        return createPostRequest.build();
    }

    @Override
    public UpdatePostRequest toUpdatePostRequest(UpdatePostRequestDto updatePostRequestDto) {
        if ( updatePostRequestDto == null ) {
            return null;
        }

        UpdatePostRequest.UpdatePostRequestBuilder updatePostRequest = UpdatePostRequest.builder();

        updatePostRequest.id( updatePostRequestDto.getId() );
        updatePostRequest.title( updatePostRequestDto.getTitle() );
        updatePostRequest.content( updatePostRequestDto.getContent() );
        updatePostRequest.categoryId( updatePostRequestDto.getCategoryId() );
        Set<UUID> set = updatePostRequestDto.getTagIds();
        if ( set != null ) {
            updatePostRequest.tagIds( new LinkedHashSet<UUID>( set ) );
        }
        updatePostRequest.status( updatePostRequestDto.getStatus() );

        return updatePostRequest.build();
    }

    protected AuthorDto userToAuthorDto(User user) {
        if ( user == null ) {
            return null;
        }

        AuthorDto.AuthorDtoBuilder authorDto = AuthorDto.builder();

        authorDto.id( user.getId() );
        authorDto.name( user.getName() );

        return authorDto.build();
    }

    protected TagResponse tagToTagResponse(Tag tag) {
        if ( tag == null ) {
            return null;
        }

        TagResponse.TagResponseBuilder tagResponse = TagResponse.builder();

        tagResponse.id( tag.getId() );
        tagResponse.name( tag.getName() );

        return tagResponse.build();
    }

    protected Set<TagResponse> tagSetToTagResponseSet(Set<Tag> set) {
        if ( set == null ) {
            return null;
        }

        Set<TagResponse> set1 = LinkedHashSet.newLinkedHashSet( set.size() );
        for ( Tag tag : set ) {
            set1.add( tagToTagResponse( tag ) );
        }

        return set1;
    }

    protected CategoryDTO categoryToCategoryDTO(Category category) {
        if ( category == null ) {
            return null;
        }

        CategoryDTO.CategoryDTOBuilder categoryDTO = CategoryDTO.builder();

        categoryDTO.id( category.getId() );
        categoryDTO.name( category.getName() );

        return categoryDTO.build();
    }
}
