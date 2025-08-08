package com.projects.blog.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;
import java.util.Objects;
import java.util.UUID;


@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "tags")
@Builder
@Getter
@Setter
public class Tag {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false , unique = true)
    private String name;

    @ManyToMany(mappedBy = "tags")
    private List<Post> posts;

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Tag tag = (Tag) o;
        return Objects.equals(id, tag.id) && Objects.equals(name, tag.name) && Objects.equals(posts, tag.posts);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, posts);
    }
}
