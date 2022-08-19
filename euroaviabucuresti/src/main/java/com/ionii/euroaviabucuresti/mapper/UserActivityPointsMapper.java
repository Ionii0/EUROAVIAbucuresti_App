package com.ionii.euroaviabucuresti.mapper;

import com.ionii.euroaviabucuresti.dto.UserActivityPointsDto;
import com.ionii.euroaviabucuresti.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel ="spring")
public interface UserActivityPointsMapper {
    @Mapping(target ="id",source = "userId")
    @Mapping(target = "firstName",source = "firstName")
    @Mapping(target = "lastName",source = "lastName")
    @Mapping(target = "activityPoints",source = "activityPoints")
    UserActivityPointsDto mapUserToUapDto(User user);
}
