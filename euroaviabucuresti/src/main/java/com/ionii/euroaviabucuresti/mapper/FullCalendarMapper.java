package com.ionii.euroaviabucuresti.mapper;

import com.ionii.euroaviabucuresti.dto.FullCalendarDto;
import com.ionii.euroaviabucuresti.model.FullCalendar;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface FullCalendarMapper {
    @Mapping(target = "id",source = "fullCalendarId")
    @Mapping(target = "title",source = "title")
    @Mapping(target = "startDate",source = "startDate")
    @Mapping(target = "endDate",source = "endDate")
    @Mapping(target = "allDay",source = "allDay")
    FullCalendarDto mapToDto(FullCalendar fullCalendar);
}
