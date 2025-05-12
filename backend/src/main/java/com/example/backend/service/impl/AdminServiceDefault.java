package com.example.backend.service.impl;

import com.example.backend.model.entity.User;
import com.example.backend.model.entity.UserRecord;
import com.example.backend.repository.RecordRepository;
import com.example.backend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminServiceDefault implements com.example.backend.service.AdminService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    RecordRepository recordRepository;

    @Override
    public List<UserRecord> getAllRecords() {
        return recordRepository.findAll();
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }
}
