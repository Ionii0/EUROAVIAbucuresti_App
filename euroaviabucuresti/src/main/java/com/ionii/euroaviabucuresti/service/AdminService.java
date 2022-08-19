package com.ionii.euroaviabucuresti.service;

import com.ionii.euroaviabucuresti.dto.UserDto;
import com.ionii.euroaviabucuresti.mapper.UserMapper;
import com.ionii.euroaviabucuresti.model.User;
import com.ionii.euroaviabucuresti.repository.UserRepository;
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
@Transactional
public class AdminService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    public List<UserDto> getAll() {
        return userRepository.findAllByOrderByLastName().stream().map(userMapper::mapUserToDto).collect(toList());
    }
    public void modifyPoints(Long userId, int nrOfPoints) {
        Optional<User> userOptional=userRepository.findByUserId(userId);
        if (userOptional.isPresent()){
            User user=userOptional.get();
            user.setActivityPoints(nrOfPoints);
        }
    }
    public void modifyTokens(Long userId, int nrOfTokens) {
        Optional<User> userOptional=userRepository.findByUserId(userId);
        if (userOptional.isPresent()){
            User user=userOptional.get();
            user.setVirtualToken(nrOfTokens);
        }
    }
}
