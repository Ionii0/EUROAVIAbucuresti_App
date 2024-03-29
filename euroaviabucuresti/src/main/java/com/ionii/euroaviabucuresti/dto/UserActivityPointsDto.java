package com.ionii.euroaviabucuresti.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserActivityPointsDto {
    private Long id;
    private String firstName;
    private String lastName;
    private int activityPoints;
}
