package com.ionii.euroaviabucuresti.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
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
@Builder
@Table(name="post")
public class Post {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long postId;
    @NotBlank(message="Title can not be empty")
    private String title;
    private int replyCount=0;
    private String url;
    private String body;
    private Instant created;
    @ManyToOne(fetch = LAZY)
    @JoinColumn(name="userId",referencedColumnName = "userId")
    private User user;
}
