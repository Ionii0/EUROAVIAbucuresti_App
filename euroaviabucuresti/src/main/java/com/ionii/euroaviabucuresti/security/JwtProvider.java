package com.ionii.euroaviabucuresti.security;

import com.ionii.euroaviabucuresti.exceptions.SpringEuroaviaException;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.io.InputStream;
import java.security.*;
import java.security.cert.CertificateException;
import java.time.Instant;
import java.util.Date;

import static java.util.Date.from;

@Service
public class JwtProvider {
    private KeyStore keyStore;
    @Value("${jwt.expiration.time}")
    private Long jwtExpirationInMillis;

    @PostConstruct
    public void init() throws SpringEuroaviaException {
        try {
            keyStore = KeyStore.getInstance("JKS");
            InputStream resourceAsStream = getClass().getResourceAsStream("/euroavia.jks");
            keyStore.load(resourceAsStream, "secret".toCharArray());
        } catch (KeyStoreException | CertificateException | NoSuchAlgorithmException | IOException e) {
            throw new SpringEuroaviaException("Exception occurred while loading keystore", e);
        }
    }


    public String generateToken(Authentication authentication) throws SpringEuroaviaException {
        org.springframework.security.core.userdetails.User principal= (org.springframework.security.core.userdetails.User) authentication.getPrincipal();
        return Jwts.builder()
                .setSubject(principal.getUsername())
                .setIssuedAt(from(Instant.now()))
                .signWith(getPrivateKey())
                .setExpiration(Date.from(Instant.now().plusMillis(jwtExpirationInMillis)))
                .compact();
    }

    public String generateTokenWithMailEuroavia(String mailEuroavia) throws SpringEuroaviaException {
        return Jwts.builder()
                .setSubject((mailEuroavia))
                .setIssuedAt(from(Instant.now()))
                .signWith(getPrivateKey())
                .setExpiration(Date.from(Instant.now().plusMillis(jwtExpirationInMillis)))
                .compact();
    }


    private PrivateKey getPrivateKey() throws SpringEuroaviaException {
        try {
            return (PrivateKey) keyStore.getKey("euroavia", "secret".toCharArray());
        } catch (KeyStoreException | NoSuchAlgorithmException | UnrecoverableKeyException e) {
            throw new SpringEuroaviaException("Exception occured while retrieving public key from keystore", e);
        }
    }
    public boolean validateToken(String jwt) throws SpringEuroaviaException {
        Jwts.parser().setSigningKey(getPublickey()).parseClaimsJws(jwt);
        return true;
    }
    private PublicKey getPublickey() throws SpringEuroaviaException {
        try {
            return keyStore.getCertificate("euroavia").getPublicKey();
        } catch (KeyStoreException e) {
            throw new SpringEuroaviaException("Exception occured while retrieving public key from keystore");
        }
    }
    public String getMailEuroaviaFromJWT(String token) throws SpringEuroaviaException {
        Claims claims = Jwts.parser()
                .setSigningKey(getPublickey())
                .parseClaimsJws(token)
                .getBody();

        return claims.getSubject();
    }
    public Long getJwtExpirationInMillis(){
        return jwtExpirationInMillis;
    }
}
