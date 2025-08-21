package com.projects.blog.services;

import com.projects.blog.entities.Post;
import com.projects.blog.entities.User;

import java.util.List;
import java.util.UUID;

public interface PostService {

    List<Post> getllPosts(UUID categoryId, UUID tagId);
    List<Post> getDraftPosts(User user);
}
