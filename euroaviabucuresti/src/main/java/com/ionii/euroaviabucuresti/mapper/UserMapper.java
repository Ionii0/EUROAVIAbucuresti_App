package com.ionii.euroaviabucuresti.mapper;

import com.ionii.euroaviabucuresti.dto.UserDto;
import com.ionii.euroaviabucuresti.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel ="spring")
public interface UserMapper {
    @Mapping(target ="id",source = "userId")
    @Mapping(target = "firstName",source = "firstName")
    @Mapping(target = "lastName",source = "lastName")
    @Mapping(target="department",source = "department")
    @Mapping(target ="virtualToken",source = "virtualToken")
    @Mapping(target = "activityPoints",source = "activityPoints")
    UserDto mapUserToDto(User user);
}
