package com.ionii.euroaviabucuresti.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

import java.time.Instant;

import static javax.persistence.FetchType.LAZY;
import static javax.persistence.GenerationType.IDENTITY;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="user")
public class User {
    @Id
    @GeneratedValue(strategy =IDENTITY)
    private Long userId;
    @NotBlank(message="First_name is required")
    private String firstName;
    @NotBlank(message="Last is required")
    private String lastName;
    @Email
    @NotEmpty(message="Personal mail is required")
    private String mailPersonal;
    @Email
    @NotEmpty(message="Euroavia mail is required")
    private String mailEuroavia;
    @NotBlank(message="Password is required")
    private String password;
    private String department;
    private String role="USER";
    private Instant created;
    private boolean enabled;
    private int virtualToken=0;
    private int activityPoints=0;

    public String getFullName(){
        String fullName=firstName+" "+lastName;
        return fullName;
    }



}
