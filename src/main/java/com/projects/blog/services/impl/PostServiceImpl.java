package com.projects.blog.services.impl;


import com.projects.blog.dtos.CreatePostRequest;
import com.projects.blog.dtos.UpdatePostRequest;
import com.projects.blog.entities.Category;
import com.projects.blog.entities.Post;
import com.projects.blog.entities.Tag;
import com.projects.blog.entities.User;
import com.projects.blog.enums.PostStatus;
import com.projects.blog.repositories.PostRepository;
import com.projects.blog.services.CategoryService;
import com.projects.blog.services.PostService;
import com.projects.blog.services.TagService;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PostServiceImpl implements PostService {

    private final PostRepository postRepository;
    private final CategoryService categoryService;
    private final TagService tagService;

    private static final int WORDS_PER_MINUTE = 200;

    @Override
    @Transactional(readOnly = true)
    public List<Post> getllPosts(UUID categoryId, UUID tagId) {
        if (categoryId != null && tagId != null){
            Category  category = categoryService.getCategoryById(categoryId);
            Tag tag = tagService.getTagById(tagId);

            postRepository.findAllByStatusAndCategoryAndTagsContaining(PostStatus.PUBLISHED, category, tag);
        }

        if (categoryId != null){
            Category  category = categoryService.getCategoryById(categoryId);
            return postRepository.findAllByStatusAndCategory(PostStatus.PUBLISHED, category);
        }

        if (tagId != null){
            Tag tag = tagService.getTagById(tagId);
            return postRepository.findAllByStatusAndTagsContains(PostStatus.PUBLISHED, tag);
        }

        return postRepository.findAllByStatus(PostStatus.PUBLISHED);
    }

    @Override
    public List<Post> getDraftPosts(User user) {
       return postRepository.findAllByAuthorAndStatus(user , PostStatus.DRAFT);
    }

    @Override
    @Transactional
    public Post createPost(User user, CreatePostRequest post) {
        Post newPost = new Post();
        newPost.setTitle(post.getTitle());
        newPost.setContent(post.getContent());
        newPost.setStatus(post.getStatus());
        newPost.setAuthor(user);

        newPost.setReadingTime(calculateReadingTime(post.getContent()));

        Category category =  categoryService.getCategoryById(post.getCategoryId());

        newPost.setCategory(category);
        Set<UUID> tagsIds = post.getTagsId();
        List<Tag> tags = tagService.getTagsByIds(tagsIds);
        newPost.setTags(new HashSet<>(tags));

        return postRepository.save(newPost);

    }

    @Override
    @Transactional
    public Post updatePost(UUID id, UpdatePostRequest updatePostRequest) {
        Post existingPost = postRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Post not found with id:" + id));

        existingPost.setTitle(updatePostRequest.getTitle());
        String postContent = updatePostRequest.getContent();
        existingPost.setContent(postContent);
        existingPost.setStatus(updatePostRequest.getStatus());
        existingPost.setReadingTime(calculateReadingTime(postContent));

        UUID updatePostRequestCategoryId = updatePostRequest.getCategoryId();
        if (!existingPost.getCategory().getId().equals(updatePostRequestCategoryId)){
            Category category = categoryService.getCategoryById(updatePostRequestCategoryId);
            existingPost.setCategory(category);
        }

        Set<UUID> existingTagIds = existingPost.getTags().stream().map(Tag::getId).collect(Collectors.toSet());

        Set<UUID> updatePostRequestTagsId = updatePostRequest.getTagIds();
        if (!existingTagIds.equals(updatePostRequestTagsId)){
            List<Tag> tags = tagService.getTagsByIds(updatePostRequestTagsId);
            existingPost.setTags(new HashSet<>(tags));
        }

        return postRepository.save(existingPost);
    }

    private Integer calculateReadingTime(String content){
        if (content == null || content.isEmpty()){
            return 0;
        }
        int wordCount = content.trim().split("\\s+").length;

      return (int) Math.ceil((double)wordCount / WORDS_PER_MINUTE);
    }


}
