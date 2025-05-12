package com.example.backend.dto;

import com.example.backend.enums.UserRole;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserRequest {

    String username;
    String email;
    String password;
    UserRole role;
}
