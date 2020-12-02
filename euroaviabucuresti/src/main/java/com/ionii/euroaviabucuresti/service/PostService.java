package com.ionii.euroaviabucuresti.service;

import com.ionii.euroaviabucuresti.dto.PostDto;
import com.ionii.euroaviabucuresti.mapper.PostMapper;
import com.ionii.euroaviabucuresti.model.Post;
import com.ionii.euroaviabucuresti.repository.PostRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

import static java.util.stream.Collectors.toList;

@Service
@AllArgsConstructor
@Slf4j
public class PostService {
    private final PostRepository postRepository;
    private final PostMapper postMapper;
    private final AuthService authService;
    @Transactional
    public PostDto save(PostDto postDto){
        Post save=postRepository.save(postMapper.mapDtoToPost(postDto, authService.getCurrentUser()));
        postDto.setId(save.getPostId());
        return postDto;
    }

    @Transactional
    public List<PostDto> getAll(){
        return postRepository.findAll().stream().map(postMapper::mapPostToDto).collect(toList());
    }
}
