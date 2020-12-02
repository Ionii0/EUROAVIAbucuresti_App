package com.ionii.euroaviabucuresti.controller;

import com.ionii.euroaviabucuresti.dto.UserDto;
import com.ionii.euroaviabucuresti.service.AdminService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/admin")
@AllArgsConstructor
public class AdminController {
    private final AdminService adminService;

    @GetMapping("/displayAllUsers")
    public ResponseEntity<List<UserDto>> getAllUsers(){
        return  ResponseEntity.status(HttpStatus.OK).body(adminService.getAll());
    }
}
