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
public class VerificationToken {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    private long tokenId;
    private String token;
    @OneToOne(fetch = LAZY)
    @JoinColumn(name="userId", referencedColumnName ="userId")
    private User user;
    private Instant expiryDate;
}
