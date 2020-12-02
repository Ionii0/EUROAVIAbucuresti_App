package com.ionii.euroaviabucuresti.controller;

import com.ionii.euroaviabucuresti.dto.CommentDto;
import com.ionii.euroaviabucuresti.service.CommentService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/comments")
@AllArgsConstructor
public class CommentController {
    private final CommentService commentService;
    @PostMapping
    public ResponseEntity<Void> createComment(@RequestBody CommentDto commentDto){
        commentService.save(commentDto);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
    @GetMapping("/forAnnouncement/{announcementId}")
    public ResponseEntity<List<CommentDto>> getAllCommentsForAnnouncement(@PathVariable Long announcementId){
       return ResponseEntity.status(HttpStatus.OK).body(commentService.getAllCommentsForAnnouncement(announcementId));

    }
}
