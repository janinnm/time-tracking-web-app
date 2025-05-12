package com.example.backend.service;

import com.example.backend.dto.RecordResponse;
import com.example.backend.model.entity.User;

import java.util.List;

public interface RecordService {

    Object createRecord(User user, String request);

    Object updateRecord(User user, Long id, String request);

    Object deleteRecord(User user, Long id);

    RecordResponse getById(User user, Long id);

    List<RecordResponse> getAll(User user);
}
