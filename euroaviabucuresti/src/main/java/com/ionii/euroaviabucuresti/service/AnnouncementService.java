package com.ionii.euroaviabucuresti.service;

import com.ionii.euroaviabucuresti.dto.AnnouncementDto;
import com.ionii.euroaviabucuresti.mapper.AnnouncementMapper;
import com.ionii.euroaviabucuresti.model.Announcement;
import com.ionii.euroaviabucuresti.repository.AnnouncementRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

import static java.util.stream.Collectors.toList;

@Service
@AllArgsConstructor
@Slf4j
public class AnnouncementService {
    private final AnnouncementRepository announcementRepository;
    private final AnnouncementMapper announcementMapper;
    private final AuthService authService;

    @Transactional
    public AnnouncementDto save(AnnouncementDto announcementDto){
      Announcement save=announcementRepository.save(announcementMapper.mapDtoToAnnouncement(announcementDto, authService.getCurrentUser()));
      announcementDto.setId(save.getAnnouncementId());
      return announcementDto;
    }

    @Transactional
    public List<AnnouncementDto> getAll(){
        return announcementRepository.findAll().stream().map(announcementMapper::mapAnnouncementToDto).collect(toList());
    }

    @Transactional
    public AnnouncementDto getAnnouncement( Long announcementId) {
        Announcement announcement= announcementRepository.findByAnnouncementId(announcementId);
        return announcementMapper.mapAnnouncementToDto(announcement);
    }
}
