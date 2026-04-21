package com.gicamube.e_commerce.service;

import com.gicamube.e_commerce.model.UserWrapper;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Jwts;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;

@Service
public class JwtService {

    @Value("${jwt.secret}")
    private String SECRET_KEY;

    @Value("${jwt.expiration}")
    private Integer expiration;

    private SecretKey getSigningKey() {
        return Keys.hmacShaKeyFor(SECRET_KEY.getBytes(StandardCharsets.UTF_8));
    }

    /// 1. Generar el token
    public String generateToken(UserWrapper username) {
        return Jwts.builder()
                .subject(username.getUsername())
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis() + expiration)) // 10 horas
                .signWith(getSigningKey())
                .compact();
    }

    /// 2. Extraer el nombre de usuario
    public String extractUsername(String token) {
        return extractAllClaims(token).getSubject();
    }

    /// 3. Validar el token
    public boolean isTokenValid(String token, String userDetailsName) {
        final String username = extractUsername(token);
        return (username.equals(userDetailsName) && !isTokenExpired(token));
    }

    private boolean isTokenExpired(String token) {
        return extractAllClaims(token).getExpiration().before(new Date());
    }

    private Claims extractAllClaims(String token) {
        return Jwts.parser()
                .verifyWith(getSigningKey())
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }
}
