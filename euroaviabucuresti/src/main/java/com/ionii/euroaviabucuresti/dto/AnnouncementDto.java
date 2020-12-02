package com.ionii.euroaviabucuresti.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AnnouncementDto {
    private Long id;
    private String title;
    private int participantCount;
    private int commentCount;
    private String body;
    private Instant created;






}
