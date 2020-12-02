package com.ionii.euroaviabucuresti.mapper;

import com.ionii.euroaviabucuresti.dto.AnnouncementDto;
import com.ionii.euroaviabucuresti.dto.PostDto;
import com.ionii.euroaviabucuresti.model.Announcement;
import com.ionii.euroaviabucuresti.model.Post;
import com.ionii.euroaviabucuresti.model.User;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface PostMapper {
    @Mapping(target = "id",source = "postId")
    @Mapping(target="title",source = "title")
    @Mapping(target ="replyCount",source = "replyCount")
    @Mapping(target = "body",source = "body")
    @Mapping(target = "created",expression ="java(java.time.Instant.now())" )
    PostDto mapPostToDto(Post post);


    @InheritInverseConfiguration
    @Mapping(target = "url",ignore = true)
    @Mapping(target = "user",source = "user")
    @Mapping(target = "created",expression ="java(java.time.Instant.now())" )
    Post mapDtoToPost(PostDto postDto, User user);
}
