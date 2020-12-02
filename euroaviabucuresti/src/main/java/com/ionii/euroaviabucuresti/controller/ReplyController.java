package com.ionii.euroaviabucuresti.controller;


import com.ionii.euroaviabucuresti.dto.ReplyDto;
import com.ionii.euroaviabucuresti.service.ReplyService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/reply")
@AllArgsConstructor
public class ReplyController {
    private final ReplyService replyService;
    @PostMapping
    public ResponseEntity<Void> createReply(@RequestBody ReplyDto replyDto){
        replyService.save(replyDto);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
    @GetMapping("/forPost/{postId}")
    public ResponseEntity<List<ReplyDto>> getAllCommentsForAnnouncement(@PathVariable Long postId){
        return ResponseEntity.status(HttpStatus.OK).body(replyService.getAllRepliesForPost(postId));

    }
}
