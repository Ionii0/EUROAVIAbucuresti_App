package com.ionii.euroaviabucuresti.controller;

import com.ionii.euroaviabucuresti.dto.UserDto;
import com.ionii.euroaviabucuresti.service.AdminService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    @GetMapping("/modifyPoints/{userId}/{nrOfPoints}")
    public ResponseEntity<Void> modifyPoints(@PathVariable Long userId, @PathVariable int nrOfPoints){
        adminService.modifyPoints(userId,nrOfPoints);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/modifyTokens/{userId}/{nrOfTokens}")
    public ResponseEntity<Void> modifyTokens(@PathVariable Long userId, @PathVariable int nrOfTokens){
        adminService.modifyTokens(userId,nrOfTokens);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
