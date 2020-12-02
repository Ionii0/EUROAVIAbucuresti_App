package com.ionii.euroaviabucuresti.controller;


import com.ionii.euroaviabucuresti.dto.PostDto;
import com.ionii.euroaviabucuresti.service.PostService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/post")
@AllArgsConstructor
@Slf4j
public class PostController {
    private final PostService postService;

    @PostMapping
    public ResponseEntity<PostDto> createPost(@RequestBody PostDto postDto){
        return ResponseEntity.status(HttpStatus.CREATED).body(postService.save(postDto));
    }

    @GetMapping
    public ResponseEntity<List<PostDto>> getAllPosts(){
        return  ResponseEntity.status(HttpStatus.OK).body(postService.getAll());
    }
}
