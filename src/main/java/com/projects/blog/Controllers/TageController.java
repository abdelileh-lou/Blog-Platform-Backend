package com.projects.blog.Controllers;


import com.projects.blog.dtos.CreateTagRequest;
import com.projects.blog.dtos.TagResponse;
import com.projects.blog.entities.Tag;
import com.projects.blog.mappers.TagMapper;
import com.projects.blog.services.impl.TageServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/tags")
@RequiredArgsConstructor
public class TageController {

    private final TagMapper tagMapper;
    private final TageServiceImpl tagService;

    @GetMapping
    public ResponseEntity<List<TagResponse>> listTags() {
          List<TagResponse> tags = tagService.getTags().stream().map(tag -> tagMapper.toTagResponse(tag)).toList();

          return ResponseEntity.ok(tags);
    }

    @PostMapping
    public ResponseEntity<List<TagResponse>> createTags(@RequestBody CreateTagRequest createTagRequests){
        List<Tag> saveTags = tagService.createTags(createTagRequests.getNames());
        List<TagResponse> tagResponses = saveTags.stream().map(tag -> tagMapper.toTagResponse(tag)).toList();

        return new ResponseEntity<>(
                tagResponses,
                HttpStatus.CREATED
        );
    }
}
