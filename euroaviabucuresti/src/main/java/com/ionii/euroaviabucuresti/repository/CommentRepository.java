package com.ionii.euroaviabucuresti.repository;

import com.ionii.euroaviabucuresti.model.Announcement;
import com.ionii.euroaviabucuresti.model.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CommentRepository extends JpaRepository<Comment,Long> {
    List<Comment> findByAnnouncement(Optional<Announcement> announcement);

}
