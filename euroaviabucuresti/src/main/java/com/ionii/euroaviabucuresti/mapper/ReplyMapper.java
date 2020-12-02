package com.ionii.euroaviabucuresti.mapper;


import com.ionii.euroaviabucuresti.dto.ReplyDto;
import com.ionii.euroaviabucuresti.model.*;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ReplyMapper {
    @Mapping(target = "replyId",ignore = true)
    @Mapping(target ="body",source="replyDto.body")
    @Mapping(target ="created",expression ="java(java.time.Instant.now())")
    @Mapping(target ="post",source="post")
    @Mapping(target = "user",source = "user")
    Reply map(ReplyDto replyDto, Post post, User user);

    @Mapping(target="postId",expression ="java(reply.getPost().getPostId())")
    @Mapping(target="mailEuroavia",expression="java(reply.getUser().getMailEuroavia())")
    @Mapping(target="id",source = "replyId")
    ReplyDto mapToDto(Reply reply);
}
