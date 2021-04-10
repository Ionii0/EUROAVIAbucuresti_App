package com.ionii.euroaviabucuresti.service;

import com.ionii.euroaviabucuresti.dto.ParticipantDto;
import com.ionii.euroaviabucuresti.mapper.ParticipantMapper;
import com.ionii.euroaviabucuresti.model.Announcement;
import com.ionii.euroaviabucuresti.model.Participant;
import com.ionii.euroaviabucuresti.model.User;
import com.ionii.euroaviabucuresti.repository.AnnouncementRepository;
import com.ionii.euroaviabucuresti.repository.ParticipantRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
@AllArgsConstructor
@Slf4j
public class ParticipantService {
    private final AnnouncementRepository announcementRepository;
    private final AuthService authService;
    private final ParticipantMapper participantMapper;
    private final ParticipantRepository participantRepository;

    @Transactional
    public void participateOrWithdraw(ParticipantDto participantDto){
        Announcement announcement= announcementRepository.findByAnnouncementId(participantDto.getAnnouncementId());
        User user=authService.getCurrentUser();
        Participant participant=participantMapper.map(participantDto,announcement,user );

      if(participantRepository.findByUserAndAnnouncement(user,announcement).isEmpty()){
            participantRepository.save(participant);
            int participantCount= announcement.getParticipantCount();
            announcement.setParticipantCount(participantCount+1);
      }
        else {
          participantRepository.deleteByUserAndAnnouncement(user, announcement);
          int participantCount = announcement.getParticipantCount();
          announcement.setParticipantCount(participantCount - 1);
      }

    }

    @Transactional
    public Boolean isParticipatingAlready(Long announcementId) {
        Announcement announcement= announcementRepository.findByAnnouncementId(announcementId);
        User user=authService.getCurrentUser();
        return !participantRepository.findByUserAndAnnouncement(user, announcement).isEmpty();
    }

    @Transactional
    public Integer countParticipants(Long announcementId) {
      Optional<Announcement> announcement= announcementRepository.findById(announcementId);
      return participantRepository.countAllByAnnouncement(announcement);
    }
}
