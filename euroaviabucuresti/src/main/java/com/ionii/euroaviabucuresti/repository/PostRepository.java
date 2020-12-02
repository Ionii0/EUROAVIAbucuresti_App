package com.ionii.euroaviabucuresti.repository;

import com.ionii.euroaviabucuresti.model.Announcement;
import com.ionii.euroaviabucuresti.model.Post;
import com.ionii.euroaviabucuresti.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PostRepository extends JpaRepository<Post,Long> {
    List<Post> findByUser(User user);
    Post findByPostId(Long id);
    Optional<Post> findById(Long id);

}
