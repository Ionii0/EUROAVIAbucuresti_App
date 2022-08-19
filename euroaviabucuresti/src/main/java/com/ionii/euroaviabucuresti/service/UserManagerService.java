package com.ionii.euroaviabucuresti.service;

import com.ionii.euroaviabucuresti.dto.UserActivityPointsDto;
import com.ionii.euroaviabucuresti.mapper.UserActivityPointsMapper;
import com.ionii.euroaviabucuresti.model.User;
import com.ionii.euroaviabucuresti.repository.UserRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;


import static java.util.stream.Collectors.toList;

@Service
@AllArgsConstructor
@Slf4j
public class UserManagerService {
    private final UserRepository userRepository;
    private final UserActivityPointsMapper userActivityPointsMapper;


    public List<UserActivityPointsDto> showRankings(){
        List<User>users=userRepository.findAll();
        return users.stream()
                .map(userActivityPointsMapper::mapUserToUapDto)
                .collect(toList());
    }




}
