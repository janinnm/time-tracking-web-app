package com.example.backend.service.impl;

import com.example.backend.model.entity.User;
import com.example.backend.model.entity.UserRecord;
import com.example.backend.repository.RecordRepository;
import com.example.backend.repository.UserRepository;
import com.example.backend.service.AdminService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminServiceDefault implements AdminService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    RecordRepository recordRepository;

    private static final Logger logger = LoggerFactory.getLogger(AdminServiceDefault.class);

    @Override
    public List<UserRecord> getAllRecords() {
        return recordRepository.findAll();
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }
}
