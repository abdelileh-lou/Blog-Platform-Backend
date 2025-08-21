package com.projects.blog.services;

import com.projects.blog.entities.Post;

import java.util.List;
import java.util.UUID;

public interface PostService {

    List<Post> getllPosts(UUID categoryId, UUID tagId);
}
