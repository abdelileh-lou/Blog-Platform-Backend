package com.projects.blog.repositories;


import com.projects.blog.entities.Tag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface TagyRepository extends JpaRepository<Tag, UUID> {
}
