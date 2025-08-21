package com.projects.blog.services.impl;


import com.projects.blog.entities.Category;
import com.projects.blog.entities.Post;
import com.projects.blog.entities.Tag;
import com.projects.blog.entities.User;
import com.projects.blog.enums.PostStatus;
import com.projects.blog.repositories.PostRepository;
import com.projects.blog.services.CategoryService;
import com.projects.blog.services.PostService;
import com.projects.blog.services.TagService;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class PostServiceImpl implements PostService {

    private final PostRepository postRepository;
    private final CategoryService categoryService;
    private final TagService tagService;

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
}
