package com.example.backend.service;

import com.example.backend.dto.UserRequest;

public interface AuthService {
    Object signup(UserRequest request);
}
