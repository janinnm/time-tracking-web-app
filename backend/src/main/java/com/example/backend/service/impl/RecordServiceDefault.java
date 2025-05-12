package com.example.backend.service.impl;

import com.example.backend.model.entity.Category;
import com.example.backend.model.entity.User;
import com.example.backend.repository.CategoryRepository;
import com.example.backend.repository.RecordRepository;
import com.example.backend.service.RecordService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import com.example.backend.model.entity.UserRecord;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class RecordServiceDefault implements RecordService {

    @Autowired
    RecordRepository recordRepository;

    @Autowired
    CategoryRepository categoryRepository;
    private static final Logger logger = LoggerFactory.getLogger(RecordServiceDefault.class);
    @Override
    public Object createRecord(User user, String request) {

        if (request.length() > 1) {
            request = request.substring(1, request.length() - 1);
        }

        String[] parts = request.split(" ", 4); // Split into max 4 parts

        int number = Integer.parseInt(parts[0]);
        String unit = parts[1];
        String categoryStr = parts[2].replaceFirst("^#", "");
        String title = parts[3];

        Category category;
        if (!categoryRepository.existsByName(categoryStr)) {
            category = Category.builder()
                    .name(categoryStr)
                    .build();
            categoryRepository.save(category);
        } else {
            category = categoryRepository.findByName(categoryStr);
        }

        UserRecord record = UserRecord.builder()
                .user(user)
                .hours(number)
                .category(category)
                .title(title)
                .build();

        recordRepository.save(record);

        return null;
    }

    @Override
    public Object updateRecord(User user, Long id, String request) {

        UserRecord record = recordRepository.findUserRecordByRecordId(id);

        if (request.length() > 1) {
            request = request.substring(1, request.length() - 1);
        }

        String[] parts = request.split(" ", 4); // Split into max 4 parts

        int number = Integer.parseInt(parts[0]);
        String unit = parts[1];
        String categoryStr = parts[2].replaceFirst("^#", "");
        String title = parts[3];

        Category category;
        if (!categoryRepository.existsByName(categoryStr)) {
            category = Category.builder()
                    .name(categoryStr)
                    .build();
            categoryRepository.save(category);
        } else {
            category = categoryRepository.findByName(categoryStr);
        }

        record = UserRecord.builder()
                .recordId(record.getRecordId())
                .hours(number)
                .category(category)
                .title(title)
                .build();

        recordRepository.save(record);

        return null;
    }

    @Override
    public Object deleteRecord(User user, Long id) {
       UserRecord record = recordRepository.findUserRecordByRecordId(id);
       recordRepository.delete(record);

       return null;
    }

    @Override
    public UserRecord getById(User user, Long id) {

        return recordRepository.findUserRecordByRecordId(id);
    }

    @Override
    public List<UserRecord> getAll(User user) {

        return recordRepository.findAllByUser(user);
    }
}
