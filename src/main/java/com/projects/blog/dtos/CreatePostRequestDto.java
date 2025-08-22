package com.projects.blog.dtos;


import com.projects.blog.enums.PostStatus;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;
import java.util.UUID;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor

public class CreatePostRequestDto {

    private UUID id;

    @NotBlank(message = "Title is required")
    @Size(min = 2, max = 100, message = "Title must be between 2 and 100 characters")
    private String title;

    @NotBlank(message = "Content is required")
    @Size(min = 2, max = 50000, message = "Content must be between 2 and 10000 characters")
    private String content;

    @NotNull(message = "Category is required")
    private UUID categoryId;



    @NotNull(message = "Tags are required")
    @Size(max = 10, message = "At least one tag is required")
    private Set<UUID> tagIds;

    @NotNull(message = "Tags are required")
    private PostStatus status;
}
