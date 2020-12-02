package com.ionii.euroaviabucuresti.service;

import com.ionii.euroaviabucuresti.dto.AuthenticationResponse;
import com.ionii.euroaviabucuresti.dto.LoginRequest;
import com.ionii.euroaviabucuresti.dto.RefreshTokenRequest;
import com.ionii.euroaviabucuresti.dto.RegisterRequest;
import com.ionii.euroaviabucuresti.exceptions.SpringEuroaviaException;
import com.ionii.euroaviabucuresti.model.NotificationEmail;
import com.ionii.euroaviabucuresti.model.User;
import com.ionii.euroaviabucuresti.model.VerificationToken;
import com.ionii.euroaviabucuresti.repository.UserRepository;
import com.ionii.euroaviabucuresti.repository.VerificationTokenRepository;
import com.ionii.euroaviabucuresti.security.JwtProvider;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.Instant;
import java.util.Optional;
import java.util.UUID;

@Service
@AllArgsConstructor
@Transactional
public class AuthService {

    private final PasswordEncoder passwordEncoder;
    private  final UserRepository userRepository;
    private final VerificationTokenRepository verificationTokenRepository;
    private final MailService mailService;
    private final AuthenticationManager authenticationManager;
    private final JwtProvider jwtProvider;
    private final RefreshTokenService refreshTokenService;

    public void signup(RegisterRequest registerRequest) throws SpringEuroaviaException {
        User user=new User();
        user.setMailEuroavia(registerRequest.getEmailEuroavia());
        user.setMailPersonal((registerRequest.getEmailPersonal()));
        user.setPassword(passwordEncoder.encode(registerRequest.getPassword()));
        user.setLastName(registerRequest.getLastName());
        user.setFirstName(registerRequest.getFirstName());
        user.setDepartment(registerRequest.getDepartment());
        user.setCreated(Instant.now());
        user.setEnabled(false);

        userRepository.save(user);

       String token= generateVerificationToken(user);
       mailService.sendMail(new NotificationEmail("Please activate your ACCOUNT",user.getMailPersonal(),"Thanks for signing up to Euroavia-Bucuresti,please click on the below url to activate your account:"+"http://localhost:8080/api/auth/accountVerification/"+token));
    }
    private String generateVerificationToken(User user){
       String token= UUID.randomUUID().toString();
        VerificationToken verificationToken=new VerificationToken();
        verificationToken.setToken(token);
        verificationToken.setUser(user);

        verificationTokenRepository.save(verificationToken);
        return token;
    }

    public void verifyAccount(String token) throws SpringEuroaviaException {
       Optional<VerificationToken> verificationToken=verificationTokenRepository.findByToken(token);
       verificationToken.orElseThrow(()->new SpringEuroaviaException("Invalid TOKEN"));
       fetchUserAndEnable(verificationToken.get());
    }

    void fetchUserAndEnable(VerificationToken verificationToken) throws SpringEuroaviaException {
      String mailEuroavia=verificationToken.getUser().getMailEuroavia();
      User user=userRepository.findByMailEuroavia(mailEuroavia).orElseThrow(()->new SpringEuroaviaException("Euroavia MAIL: "+mailEuroavia +" not found !"));
      user.setEnabled(true);
      userRepository.save(user);
    }

    public AuthenticationResponse login(LoginRequest loginRequest) throws SpringEuroaviaException {
       Authentication authenticate=  authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getMailEuroavia(),loginRequest.getPassword()));
       SecurityContextHolder.getContext().setAuthentication(authenticate);
       String token=jwtProvider.generateToken(authenticate);
       return AuthenticationResponse.builder()
               .authenticationToken(token)
               .refreshToken(refreshTokenService.generateRefreshToken().getToken())
               .expiresAt(Instant.now().plusMillis(jwtProvider.getJwtExpirationInMillis()))
               .mailEuroavia(loginRequest.getMailEuroavia())
               .build();
    }
    public User getCurrentUser() {
        org.springframework.security.core.userdetails.User principal = (org.springframework.security.core.userdetails.User) SecurityContextHolder.
                getContext().getAuthentication().getPrincipal();
        return userRepository.findByMailEuroavia(principal.getUsername())
                .orElseThrow(() -> new UsernameNotFoundException("User name not found - " + principal.getUsername()));
    }

    public AuthenticationResponse refreshToken(RefreshTokenRequest refreshTokenRequest) throws SpringEuroaviaException {
        refreshTokenService.validateRefreshToken(refreshTokenRequest.getRefreshToken());
        String token=jwtProvider.generateTokenWithMailEuroavia(refreshTokenRequest.getMailEuroavia());
        return AuthenticationResponse.builder()
                .authenticationToken(token)
                .refreshToken(refreshTokenRequest.getRefreshToken())
                .expiresAt(Instant.now().plusMillis(jwtProvider.getJwtExpirationInMillis()))
                .mailEuroavia(refreshTokenRequest.getMailEuroavia())
                .build();
    }

}
