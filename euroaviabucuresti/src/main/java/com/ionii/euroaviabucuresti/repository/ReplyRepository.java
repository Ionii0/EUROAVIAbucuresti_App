package com.ionii.euroaviabucuresti.repository;

import com.ionii.euroaviabucuresti.model.Post;
import com.ionii.euroaviabucuresti.model.Reply;
import com.ionii.euroaviabucuresti.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ReplyRepository extends JpaRepository<Reply,Long> {
    List<Reply> findByPost(Optional<Post> post);

    List<Reply> findAllByUser(User user);
}
