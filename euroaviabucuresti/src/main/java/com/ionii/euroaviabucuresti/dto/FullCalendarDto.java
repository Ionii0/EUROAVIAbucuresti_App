package com.ionii.euroaviabucuresti.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FullCalendarDto {
    private long Id;
    private String title;
    private String startDate;
    private String endDate;
    private Boolean allDay;
}
