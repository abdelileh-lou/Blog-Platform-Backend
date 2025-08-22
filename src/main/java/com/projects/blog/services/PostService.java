package com.projects.blog.services;

import com.projects.blog.dtos.CreatePostRequest;
import com.projects.blog.dtos.UpdatePostRequest;
import com.projects.blog.entities.Post;
import com.projects.blog.entities.User;

import java.util.List;
import java.util.UUID;

public interface PostService {

    Post getPostById(UUID id);
    List<Post> getllPosts(UUID categoryId, UUID tagId);
    List<Post> getDraftPosts(User user);


    Post createPost(User user , CreatePostRequest post);

    void deletePost(UUID id);
    Post updatePost(UUID id , UpdatePostRequest updatePostRequest);
}
