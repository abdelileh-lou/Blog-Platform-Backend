package com.projects.blog.repositories;



import com.projects.blog.entities.Category;
import com.projects.blog.entities.Post;
import com.projects.blog.entities.Tag;
import com.projects.blog.entities.User;
import com.projects.blog.enums.PostStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface PostRepository extends JpaRepository<Post, UUID> {

    List<Post> findAllByStatusAndCategoryAndTagsContaining(PostStatus status , Category category , Tag tag);
    List<Post> findAllByStatusAndCategory(PostStatus status , Category category);

    List<Post> findAllByStatusAndTagsContains(PostStatus status , Tag tag);

    List<Post> findAllByStatus(PostStatus status);

    List<Post> findAllByAuthorAndStatus(User user , PostStatus status );
}
