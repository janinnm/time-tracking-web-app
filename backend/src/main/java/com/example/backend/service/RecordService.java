package com.example.backend.service;

import com.example.backend.model.entity.User;
import com.example.backend.model.entity.UserRecord;

import java.util.List;

public interface RecordService {

    Object createRecord(User user, String request);

    Object updateRecord(User user, Long id, String request);

    Object deleteRecord(User user, Long id);

    UserRecord getById(User user, Long id);

    List<UserRecord> getAll(User user);
}
