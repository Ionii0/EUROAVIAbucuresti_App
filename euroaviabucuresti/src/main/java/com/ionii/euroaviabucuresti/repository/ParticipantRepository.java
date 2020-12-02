package com.ionii.euroaviabucuresti.repository;

import com.ionii.euroaviabucuresti.model.Announcement;
import com.ionii.euroaviabucuresti.model.Participant;
import com.ionii.euroaviabucuresti.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ParticipantRepository extends JpaRepository<Participant,Long> {

   List<Participant> findByUserAndAnnouncement(User user, Announcement announcement);

   Integer countAllByAnnouncement(Optional<Announcement> announcement);
}
