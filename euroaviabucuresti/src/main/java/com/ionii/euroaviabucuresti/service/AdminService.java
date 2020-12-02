package com.ionii.euroaviabucuresti.service;

import com.ionii.euroaviabucuresti.dto.UserDto;
import com.ionii.euroaviabucuresti.mapper.UserMapper;
import com.ionii.euroaviabucuresti.repository.UserRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import static java.util.stream.Collectors.toList;

@Service
@AllArgsConstructor
@Slf4j
public class AdminService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    @Transactional
    public List<UserDto> getAll() {
        return userRepository.findAllByOrderByLastName().stream().map(userMapper::mapUserToDto).collect(toList());
    }
}
