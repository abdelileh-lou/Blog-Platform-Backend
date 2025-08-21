package com.projects.blog.Controllers;


import com.projects.blog.dtos.TagResponse;
import com.projects.blog.mappers.TagMapper;
import com.projects.blog.services.impl.TageServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
