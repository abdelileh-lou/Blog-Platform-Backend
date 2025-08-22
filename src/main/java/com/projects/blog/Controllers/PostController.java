package com.projects.blog.Controllers;


import com.projects.blog.dtos.*;
import com.projects.blog.entities.Post;
import com.projects.blog.entities.User;
import com.projects.blog.mappers.PostMapper;
import com.projects.blog.services.PostService;
import com.projects.blog.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/posts")
@RequiredArgsConstructor

public class PostController {
    private final PostService postService;
    private final PostMapper postMapper;
    private final UserService userService;

    @GetMapping
    public ResponseEntity<List<PostDto>> getAllPosts(
            @RequestParam(required = false)UUID categoryId,
            @RequestParam(required = false)UUID tagId
            ){
        List<Post> posts = postService.getllPosts(categoryId, tagId);

      List<PostDto> postDtos =  posts.stream().map(post -> postMapper.toPostDto(post)).toList();
      return ResponseEntity.ok(postDtos);
    }


    @GetMapping(path =  "/drafts")
    public ResponseEntity<List<PostDto>> getDrafts(@RequestAttribute UUID userId){
        User loggedInUser = userService.getUserById(userId);
       List<Post>  draftPost = postService.getDraftPosts(loggedInUser);
       List<PostDto> postDtos = draftPost.stream().map(post -> postMapper.toPostDto(post)).toList();
       return ResponseEntity.ok(postDtos);
    }


    @PostMapping
    public ResponseEntity<PostDto> createPost(@RequestBody CreatePostRequestDto createPostRequestDto , @RequestAttribute UUID userId){
        User loggedInUser = userService.getUserById(userId);

        CreatePostRequest createPostRequest = postMapper.toCreatePostRequest(createPostRequestDto);
        Post createPost = postService.createPost(loggedInUser, createPostRequest);

        PostDto postDto = postMapper.toPostDto(createPost);
        return new ResponseEntity<>(postDto ,HttpStatus.CREATED);


    }



    @PutMapping(path = "/{id}")
    public ResponseEntity<PostDto> updatePost(@PathVariable UUID id
            , @RequestBody UpdatePostRequestDto updatePostRequestDto
            , @RequestAttribute UUID userId){
        UpdatePostRequest updatePostRequest = postMapper.toUpdatePostRequest(updatePostRequestDto);
        Post updatePost = postService.updatePost(id, updatePostRequest);
        PostDto postDto = postMapper.toPostDto(updatePost);
        return new ResponseEntity<>(postDto , HttpStatus.OK);

    }


    @GetMapping("/{id}")
    public ResponseEntity<PostDto> getPost(@PathVariable UUID id){
        Post postById = postService.getPostById(id);
        PostDto postDto = postMapper.toPostDto(postById);
        return ResponseEntity.ok(postDto);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePost(@PathVariable UUID id){
        postService.deletePost(id);
        return ResponseEntity.noContent().build();
    }

}
