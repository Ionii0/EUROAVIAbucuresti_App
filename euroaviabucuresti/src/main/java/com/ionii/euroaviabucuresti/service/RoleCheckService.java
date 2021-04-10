package com.ionii.euroaviabucuresti.service;

import com.ionii.euroaviabucuresti.model.User;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
@Slf4j
public class RoleCheckService {
    private final AuthService authService;
    public Boolean isAdmin() {
       User user=authService.getCurrentUser();
       return user.getRole().equals("ADMIN");
    }
}
