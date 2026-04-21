package com.gicamube.e_commerce.service;

import com.gicamube.e_commerce.model.UserWrapper;
import com.gicamube.e_commerce.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.*;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserWrapper loadUserByUsername(String email)
            throws UsernameNotFoundException {

        return userRepository.findByEmail(email)
                .map(UserWrapper::new)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
    }
}
