package com.projects.blog.services.impl;

import com.projects.blog.entities.Tag;
import com.projects.blog.repositories.TagRepository;
import com.projects.blog.services.TagService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TageServiceImpl implements TagService {

    private final TagRepository tagRepository;

    @Override
    public List<Tag> getTags() {
        return tagRepository.findAllWithPostCount();
    }
}
