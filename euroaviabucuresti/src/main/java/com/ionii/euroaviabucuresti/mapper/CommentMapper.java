package com.ionii.euroaviabucuresti.mapper;

import com.ionii.euroaviabucuresti.dto.CommentDto;
import com.ionii.euroaviabucuresti.model.Announcement;
import com.ionii.euroaviabucuresti.model.Comment;
import com.ionii.euroaviabucuresti.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CommentMapper {
    @Mapping(target = "commentId",ignore = true)
    @Mapping(target ="body",source="commentDto.body")
    @Mapping(target ="created",expression ="java(java.time.Instant.now())")
    @Mapping(target ="announcement",source="announcement")
    @Mapping(target = "user",source = "user")
    Comment map(CommentDto commentDto, Announcement announcement, User user);

    @Mapping(target="announcementId",expression ="java(comment.getAnnouncement().getAnnouncementId())")
    @Mapping(target="mailEuroavia",expression="java(comment.getUser().getMailEuroavia())")
    @Mapping(target="id",source = "commentId")
    CommentDto mapToDto(Comment comment);
}
