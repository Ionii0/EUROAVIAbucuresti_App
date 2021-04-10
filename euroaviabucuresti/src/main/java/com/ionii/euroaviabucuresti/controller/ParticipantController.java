package com.ionii.euroaviabucuresti.controller;

import com.ionii.euroaviabucuresti.dto.ParticipantDto;
import com.ionii.euroaviabucuresti.service.ParticipantService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/participant")
@AllArgsConstructor
public class ParticipantController {
    private final ParticipantService participantService;
    @PostMapping("/participateOrWithdraw")
    public ResponseEntity<Void> participateOrWithdraw(@RequestBody ParticipantDto participantDto){
        participantService.participateOrWithdraw(participantDto);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/participationStatus/{announcementId}")
    public ResponseEntity<String> isParticipatingAlready(@PathVariable Long announcementId){
       if (participantService.isParticipatingAlready(announcementId))
        return ResponseEntity.status(HttpStatus.OK).body("true");

        return ResponseEntity.status(HttpStatus.OK).body("false");
    }

    @GetMapping("/countParticipantsForAnnouncement/{announcementId}")
    public ResponseEntity<Integer> countParticipants(@PathVariable Long announcementId){
        return new ResponseEntity<>(participantService.countParticipants(announcementId), HttpStatus.OK);
    }

}
