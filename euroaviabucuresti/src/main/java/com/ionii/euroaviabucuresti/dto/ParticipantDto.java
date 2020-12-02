package com.ionii.euroaviabucuresti.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ParticipantDto {
    private Long id;
    private Long announcementId;
    private String mailEuroavia;

}
