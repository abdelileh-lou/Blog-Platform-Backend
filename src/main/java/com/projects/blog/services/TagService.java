package com.projects.blog.services;

import com.projects.blog.entities.Tag;

import java.util.List;
import java.util.Set;
import java.util.UUID;

public interface TagService {

    List<Tag> getTags();
    List<Tag> createTags(Set<String> tagNames);

    void deleteTag(UUID id);
}
