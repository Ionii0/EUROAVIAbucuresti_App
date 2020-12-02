package com.ionii.euroaviabucuresti.mapper;

import com.ionii.euroaviabucuresti.dto.ParticipantDto;
import com.ionii.euroaviabucuresti.model.Announcement;
import com.ionii.euroaviabucuresti.model.Participant;
import com.ionii.euroaviabucuresti.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ParticipantMapper {
    @Mapping(target = "participantId",ignore = true)
    @Mapping(target = "created", expression = "java(java.time.Instant.now())")
    @Mapping(target = "announcement", source="announcement")
    @Mapping(target = "user",source = "user")
    Participant map(ParticipantDto participantDto, Announcement announcement, User user);

    @Mapping(target = "announcementId", expression = "java(participant.getAnnouncement().getAnnouncementId())")
    @Mapping(target="mailEuroavia",expression="java(participant.getUser().getMailEuroavia())")
    @Mapping(target ="id",source ="participantId" )
    ParticipantDto mapToDto(Participant participant);
}
