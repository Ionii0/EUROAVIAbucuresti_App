package com.ionii.euroaviabucuresti.service;

import com.ionii.euroaviabucuresti.dto.CommentDto;
import com.ionii.euroaviabucuresti.mapper.CommentMapper;
import com.ionii.euroaviabucuresti.model.Announcement;
import com.ionii.euroaviabucuresti.model.Comment;
import com.ionii.euroaviabucuresti.repository.AnnouncementRepository;
import com.ionii.euroaviabucuresti.repository.CommentRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import static java.util.stream.Collectors.toList;

@Service
@AllArgsConstructor
public class CommentService {
    private final AnnouncementRepository announcementRepository;
    private final AuthService authService;
    private final CommentMapper commentMapper;
    private final CommentRepository commentRepository;

    public void save(CommentDto commentDto){
     Announcement announcement= announcementRepository.findByAnnouncementId(commentDto.getAnnouncementId());
     int commentCount= announcement.getCommentCount();
     announcement.setCommentCount(commentCount+1);
     Comment comment=commentMapper.map(commentDto,announcement, authService.getCurrentUser());
     commentRepository.save(comment);

    }
    public List<CommentDto> getAllCommentsForAnnouncement(Long announcementId){
      Optional<Announcement> announcement= announcementRepository.findById(announcementId);
      return commentRepository.findByAnnouncement(announcement)
               .stream()
               .map(commentMapper::mapToDto)
               .collect(toList());
    }
}
