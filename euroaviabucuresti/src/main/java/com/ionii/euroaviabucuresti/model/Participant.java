package com.ionii.euroaviabucuresti.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

import java.time.Instant;

import static javax.persistence.FetchType.LAZY;
import static javax.persistence.GenerationType.IDENTITY;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="participant")
public class Participant {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long participantId;
    @ManyToOne(fetch = LAZY)
    @JoinColumn(name="announcementId", referencedColumnName = "announcementId")
    private Announcement announcement;
    private Instant created;
    @ManyToOne(fetch = LAZY)
    @JoinColumn(name="userId",referencedColumnName = "userId")
    private User user;

}
