package com.ionii.euroaviabucuresti.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserDto {
    private Long id;
    private String firstName;
    private String lastName;
    private String mailEuroavia;
    private String department;
    private int virtualToken;
    private int activityPoints;
}
