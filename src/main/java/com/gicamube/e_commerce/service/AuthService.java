package com.gicamube.e_commerce.service;

import com.gicamube.e_commerce.config.SecurityConfig;
import com.gicamube.e_commerce.dto.AuthResponse;
import com.gicamube.e_commerce.dto.LoginRequest;
import com.gicamube.e_commerce.dto.RegisterRequest;
import com.gicamube.e_commerce.model.User;
import com.gicamube.e_commerce.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final JwtService jwtService;
    private final CustomUserDetailsService userDetailsService;
    @Autowired
    private final PasswordEncoder passwordEncoder;

    public AuthResponse register(RegisterRequest request) {

        if (userRepository.findByEmail(request.getEmail()).isPresent()) {
            throw new RuntimeException("Email ya registrado en el sistema");
        }

        User user = User.builder()
                .name(request.getName())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .build();

        userRepository.save(user);

        var userDetails = userDetailsService.loadUserByUsername(user.getEmail());

        String token = jwtService.generateToken(userDetails);

        return new AuthResponse(token);
    }

    public AuthResponse login(LoginRequest request) {

        var userDetails = userDetailsService
                .loadUserByUsername(request.getEmail());

        if (!passwordEncoder.matches(request.getPassword(), userDetails.getPassword())) {
            throw new RuntimeException("Credenciales invalidas");
        }

        String token = jwtService.generateToken(userDetails);

        return new AuthResponse(token);
    }
}
