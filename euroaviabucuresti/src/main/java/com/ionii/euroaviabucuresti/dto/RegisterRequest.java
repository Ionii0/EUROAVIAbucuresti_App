package com.ionii.euroaviabucuresti.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequest {
    private String emailPersonal;
    private String emailEuroavia;
    private String password;
    private String department;
    private String firstName;
    private String lastName;
}
