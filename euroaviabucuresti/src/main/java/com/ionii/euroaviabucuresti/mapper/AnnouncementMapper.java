package com.ionii.euroaviabucuresti.mapper;

import com.ionii.euroaviabucuresti.dto.AnnouncementDto;
import com.ionii.euroaviabucuresti.model.Announcement;

import com.ionii.euroaviabucuresti.model.User;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;




@Mapper(componentModel = "spring")
public interface AnnouncementMapper {
    @Mapping(target = "id",source = "announcementId")
    @Mapping(target="title",source = "title")
    @Mapping(target ="participantCount",source = "participantCount")
    @Mapping(target = "commentCount",source = "commentCount")
    @Mapping(target = "body",source = "body")
    @Mapping(target = "created",expression ="java(java.time.Instant.now())" )
    AnnouncementDto mapAnnouncementToDto(Announcement announcement);


    @InheritInverseConfiguration
    @Mapping(target = "url",ignore = true)
    @Mapping(target = "user",source = "user")
    @Mapping(target = "created",expression ="java(java.time.Instant.now())" )
    Announcement mapDtoToAnnouncement(AnnouncementDto announcementDto, User user);

}
