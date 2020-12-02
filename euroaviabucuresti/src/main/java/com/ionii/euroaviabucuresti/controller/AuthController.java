package com.ionii.euroaviabucuresti.controller;

import com.ionii.euroaviabucuresti.dto.AuthenticationResponse;
import com.ionii.euroaviabucuresti.dto.LoginRequest;
import com.ionii.euroaviabucuresti.dto.RefreshTokenRequest;
import com.ionii.euroaviabucuresti.dto.RegisterRequest;
import com.ionii.euroaviabucuresti.exceptions.SpringEuroaviaException;
import com.ionii.euroaviabucuresti.service.AuthService;
import com.ionii.euroaviabucuresti.service.RefreshTokenService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import javax.validation.Valid;

@RestController
@RequestMapping("/api/auth")
@AllArgsConstructor
public class AuthController {
    private final AuthService authService;
    private final RefreshTokenService refreshTokenService;

    @PostMapping("/signup")
    public ResponseEntity<String> signup(@RequestBody RegisterRequest registerRequest) throws SpringEuroaviaException {

        authService.signup(registerRequest);
        return new ResponseEntity<>("User REGISTRATION Succesfull!", HttpStatus.OK);
    }

    @GetMapping("/accountVerification/{token}")
    public ResponseEntity<String> verifyAccount(@PathVariable String token) throws SpringEuroaviaException {
        authService.verifyAccount(token);
        return new ResponseEntity<>("ACCOUNT Activated Successfully!", HttpStatus.OK);
    }

    @PostMapping("/login")
    public AuthenticationResponse login(@RequestBody LoginRequest loginRequest) throws SpringEuroaviaException {
       return authService.login(loginRequest);
    }

    @PostMapping("/refreshToken")
    public AuthenticationResponse refreshToken(@Valid @RequestBody RefreshTokenRequest refreshTokenRequest) throws SpringEuroaviaException {
        return authService.refreshToken(refreshTokenRequest);
    }
    @PostMapping("/logout")
    public ResponseEntity<String> logout(@Valid @RequestBody RefreshTokenRequest refreshTokenRequest){
        refreshTokenService.deleteRefreshToken(refreshTokenRequest.getRefreshToken());
        return ResponseEntity.status(HttpStatus.OK).body("Refresh Token Deleted Successfully!");
    }
}
