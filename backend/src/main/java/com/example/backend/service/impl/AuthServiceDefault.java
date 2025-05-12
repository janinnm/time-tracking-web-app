package com.example.backend.service.impl;

import com.example.backend.dto.LoginRequest;
import com.example.backend.dto.UserRequest;
import com.example.backend.enums.ResultCode;
import com.example.backend.enums.UserRole;
import com.example.backend.exception.CustomErrorException;
import com.example.backend.model.ResponseVO;
import com.example.backend.model.entity.User;
import com.example.backend.repository.UserRepository;
import com.example.backend.security.JwtUtil;
import com.example.backend.service.AuthService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class AuthServiceDefault implements AuthService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    private static final Logger logger = LoggerFactory.getLogger(AuthServiceDefault.class);


    @Override
    public Object signup(UserRequest request) {
        try {
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
        } catch (Exception e) {
            throw new CustomErrorException(ResultCode.RSC_4000);
        }
    }

}
