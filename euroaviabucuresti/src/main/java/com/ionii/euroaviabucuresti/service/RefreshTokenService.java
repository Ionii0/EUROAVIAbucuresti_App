package com.ionii.euroaviabucuresti.service;

import com.ionii.euroaviabucuresti.exceptions.SpringEuroaviaException;
import com.ionii.euroaviabucuresti.model.RefreshToken;
import com.ionii.euroaviabucuresti.repository.RefreshTokenRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.Instant;
import java.util.UUID;

@Service
@AllArgsConstructor
@Transactional
public class RefreshTokenService {
    private final RefreshTokenRepository refreshTokenRepository;

    public RefreshToken generateRefreshToken(){
        RefreshToken refreshToken=new RefreshToken();
        refreshToken.setToken(UUID.randomUUID().toString());
        refreshToken.setCreated(Instant.now());
        return refreshTokenRepository.save(refreshToken);
    }

    void validateRefreshToken (String token) throws SpringEuroaviaException {
        refreshTokenRepository.findByToken(token).orElseThrow(()->new SpringEuroaviaException("Invalid refresh token"));
    }

    public void deleteRefreshToken(String token){
        refreshTokenRepository.deleteByToken(token);
    }

}
