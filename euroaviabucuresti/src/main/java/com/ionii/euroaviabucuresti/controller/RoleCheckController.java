package com.ionii.euroaviabucuresti.controller;


import com.ionii.euroaviabucuresti.service.RoleCheckService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/roleCheck")
@AllArgsConstructor
@Slf4j
public class RoleCheckController {
    private final RoleCheckService roleCheckService;

    @PostMapping
    public ResponseEntity<String> roleCheck(){
        if(roleCheckService.isAdmin())
        return ResponseEntity.status(HttpStatus.OK).body("true");

        return ResponseEntity.status(HttpStatus.OK).body("false");
    }
}
