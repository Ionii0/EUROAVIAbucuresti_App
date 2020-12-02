package com.ionii.euroaviabucuresti.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReplyDto {
    private Long id;
    private Long postId;
    private Instant created;
    private String body;
    private String mailEuroavia;
}
