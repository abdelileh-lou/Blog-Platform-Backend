package com.projects.blog.Controllers;


import com.projects.blog.dtos.PostDto;
import com.projects.blog.entities.Post;
import com.projects.blog.mappers.PostMapper;
import com.projects.blog.services.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/posts")
@RequiredArgsConstructor

public class PostController {
    private final PostService postService;
    private PostMapper postMapper;

    @GetMapping
    public ResponseEntity<List<PostDto>> getAllPosts(
            @RequestParam(required = false)UUID categoryId,
            @RequestParam(required = false)UUID tagId
            ){
        List<Post> posts = postService.getllPosts(categoryId, tagId);

      List<PostDto> postDtos =  posts.stream().map(post -> postMapper.toPostDto(post)).toList();
      return ResponseEntity.ok(postDtos);
    }
}
