package com.ionii.euroaviabucuresti.service;


import com.ionii.euroaviabucuresti.dto.ReplyDto;

import com.ionii.euroaviabucuresti.mapper.ReplyMapper;


import com.ionii.euroaviabucuresti.model.Post;
import com.ionii.euroaviabucuresti.model.Reply;
import com.ionii.euroaviabucuresti.repository.*;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

import static java.util.stream.Collectors.toList;

@Service
@AllArgsConstructor
@Slf4j
public class ReplyService {
    private final PostRepository postRepository;
    private final AuthService authService;
    private final ReplyMapper replyMapper;
    private final ReplyRepository replyRepository;

    @Transactional
    public void save(ReplyDto replyDto){
        Post post= postRepository.findByPostId(replyDto.getPostId());
        Reply reply=replyMapper.map(replyDto,post, authService.getCurrentUser());
        replyRepository.save(reply);
    }
    @Transactional
    public List<ReplyDto> getAllRepliesForPost(Long postId){
        Optional<Post> post= postRepository.findById(postId);
        return replyRepository.findByPost(post)
                .stream()
                .map(replyMapper::mapToDto)
                .collect(toList());
    }


}
