package com.example.backend.service.impl;

import com.example.backend.dto.UserRequest;
import com.example.backend.enums.UserRole;
import com.example.backend.model.entity.User;
import com.example.backend.repository.UserRepository;
import com.example.backend.service.AuthService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceDefault implements AuthService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    private static final Logger logger = LoggerFactory.getLogger(AuthServiceDefault.class);

    @Override
    public Object signup(UserRequest request) {
        if (userRepository.findByUsername(request.getUsername()).isPresent()) {
            return "Username already taken";
        }

        String encodedPassword = passwordEncoder.encode(request.getPassword());

        logger.info(String.valueOf(request.getRole()));

        User user = User.builder()
                .username(request.getUsername())
                .email(request.getEmail())
                .password(encodedPassword) // Save the hashed password
                .role(request.getRole().name().toLowerCase())
                .build();

        userRepository.save(user);
        return "Signup successful";
    }
}
