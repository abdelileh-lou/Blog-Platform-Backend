package com.projects.blog.dtos;


import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.util.Set;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateTagRequest {

    @NotEmpty(message = "Tag names are required")
    @Size(max = 10, message = "Maximum {max} tags allowed")

    private Set<
            @Size(min = 2, max = 30, message = "Tag name must be between 2 and 30 characters")
            @Pattern(regexp = "^[\\w\\s-]+$", message = "Category name can only contain alphanumeric characters, spaces, dashes and underscores")
                String> names;
}
