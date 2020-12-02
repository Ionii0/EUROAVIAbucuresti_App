package com.ionii.euroaviabucuresti.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommentDto {
    private Long id;
    private Long announcementId;
    private Instant created;
    private String body;
    private String mailEuroavia;

}
