package com.ionii.euroaviabucuresti.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoginRequest {
    private String mailEuroavia;
    private String password;

}
