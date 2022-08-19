package com.ionii.euroaviabucuresti.controller;

import com.ionii.euroaviabucuresti.dto.UserActivityPointsDto;
import com.ionii.euroaviabucuresti.service.UserManagerService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/userManager")
@AllArgsConstructor
@Slf4j
public class UserManagerController {
    private final UserManagerService userManagerService;
    @PostMapping("/showRankings")
    public ResponseEntity<List<UserActivityPointsDto>> showRankings(){

        return ResponseEntity.status(HttpStatus.OK).body(userManagerService.showRankings());
    }

}
