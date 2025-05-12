package com.example.backend.service;

import com.example.backend.model.entity.User;
import com.example.backend.model.entity.UserRecord;

import java.util.List;

public interface AdminService {
    List<UserRecord> getAllRecords();

    List<User> getAllUsers();
}
