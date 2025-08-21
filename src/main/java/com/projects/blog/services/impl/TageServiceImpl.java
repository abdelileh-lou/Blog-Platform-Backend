package com.projects.blog.services.impl;

import com.projects.blog.entities.Tag;
import com.projects.blog.repositories.TagRepository;
import com.projects.blog.services.TagService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;
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

    @Override
    public Tag getTagById(UUID id) {
       return tagRepository.findById(id).orElseThrow(()-> new EntityNotFoundException("Tag not found with id:" + id));
    }

    @Override
    public void deleteTag(UUID id) {
        tagRepository.findById(id).ifPresent(tag -> {
           if (!tag.getPosts().isEmpty()){
               throw new IllegalStateException("Tag has posts associated with it. Cannot be deleted");
           }
           tagRepository.deleteById(id);
        });
    }
}
