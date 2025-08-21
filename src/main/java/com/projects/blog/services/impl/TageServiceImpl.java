package com.projects.blog.services.impl;

import com.projects.blog.entities.Tag;
import com.projects.blog.repositories.TagRepository;
import com.projects.blog.services.TagService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class TageServiceImpl implements TagService {

    private final TagRepository tagRepository;

    @Override
    public List<Tag> getTags() {
        return tagRepository.findAllWithPostCount();
    }



    @Transactional
    @Override
    public List<Tag> createTags(Set<String> tagNames) {
        List<Tag>  existing = tagRepository.findByNameIn(tagNames);
        Set<String> existingTagNames = existing.stream().map(Tag::getName).collect(Collectors.toSet());

        List<Tag> newTags = tagNames.stream().filter(name -> !existingTagNames.contains(name)).map(name -> Tag.builder().name(name).posts(new HashSet<>()).build()).collect(Collectors.toList());

        List<Tag> saveTags = new ArrayList<>();
        if(!newTags.isEmpty()){
            tagRepository.saveAll(newTags);
        }

        saveTags.addAll(existing);
        return  saveTags;

    }
}
