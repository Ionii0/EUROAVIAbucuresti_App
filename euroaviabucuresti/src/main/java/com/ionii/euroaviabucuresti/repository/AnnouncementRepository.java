package com.ionii.euroaviabucuresti.repository;

import com.ionii.euroaviabucuresti.model.Announcement;
import com.ionii.euroaviabucuresti.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AnnouncementRepository extends JpaRepository<Announcement,Long> {
    List<Announcement> findByUser(User user);
    Announcement findByAnnouncementId(Long id);
    Optional<Announcement> findById(Long id);
    List<Announcement> findByOrderByAnnouncementIdDesc();
}
