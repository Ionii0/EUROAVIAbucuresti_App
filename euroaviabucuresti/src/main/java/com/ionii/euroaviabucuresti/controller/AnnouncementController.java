package com.ionii.euroaviabucuresti.controller;

import com.ionii.euroaviabucuresti.dto.AnnouncementDto;
import com.ionii.euroaviabucuresti.service.AnnouncementService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/announcement")
@AllArgsConstructor
@Slf4j
public class AnnouncementController {
    private final AnnouncementService announcementService;

    @PostMapping
    public ResponseEntity<AnnouncementDto>createAnnouncement(@RequestBody AnnouncementDto announcementDto){
       return ResponseEntity.status(HttpStatus.CREATED).body(announcementService.save(announcementDto));
    }

    @GetMapping
    public ResponseEntity<List<AnnouncementDto>> getAllAnnouncements(){
     return  ResponseEntity.status(HttpStatus.OK).body(announcementService.getAll());
    }
    @GetMapping("/{announcementId}")
    public ResponseEntity<AnnouncementDto> getAnnouncement(@PathVariable Long announcementId){
        return  ResponseEntity.status(HttpStatus.OK).body(announcementService.getAnnouncement(announcementId));
    }

}
