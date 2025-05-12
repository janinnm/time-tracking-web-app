package com.example.backend.service.impl;

import com.example.backend.dto.RecordResponse;
import com.example.backend.enums.ResultCode;
import com.example.backend.exception.CustomErrorException;
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

import java.time.LocalDate;
import java.util.ArrayList;
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

        String[] parts = request.split(" ", 4);

        if (parts.length < 4) {
            throw new CustomErrorException(ResultCode.RSC_4001);
        }

        int number = Integer.parseInt(parts[0]);
        String categoryStr = parts[2].replaceFirst("^#", "").toLowerCase();
        String title = parts[3];

        Category category;
        if (!categoryRepository.existsByNameIgnoreCase(categoryStr)) {
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

        if (parts.length < 4) {
            throw new CustomErrorException(ResultCode.RSC_4001);
        }


        int number = Integer.parseInt(parts[0]);
        String categoryStr = parts[2].replaceFirst("^#", "").toLowerCase();
        String title = parts[3];

        Category category;
        if (!categoryRepository.existsByNameIgnoreCase(categoryStr)) {
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
    public RecordResponse getById(User user, Long id) {

        UserRecord record = recordRepository.findUserRecordByRecordId(id);

        return RecordResponse.builder()
                .recordId(record.getRecordId())
                .userId(record.getUser().getUserId())
                .username(record.getUser().getUsername())
                .categoryId(record.getCategory().getCategoryId())
                .categoryName(record.getCategory().getName())
                .title(record.getTitle())
                .hours(record.getHours())
                .date(LocalDate.from(record.getDate()))
                .build();
    }

    @Override
    public List<RecordResponse> getAll(User user) {

        List<UserRecord> recordList = recordRepository.findAllByUser(user);
        List<RecordResponse> recordResponses = new ArrayList<>();
        for (UserRecord record : recordList) {
            RecordResponse recordResponse = RecordResponse.builder()
                    .recordId(record.getRecordId())
                    .userId(record.getUser().getUserId())
                    .username(record.getUser().getUsername())
                    .categoryId(record.getCategory().getCategoryId())
                    .categoryName(record.getCategory().getName())
                    .title(record.getTitle())
                    .hours(record.getHours())
                    .date(LocalDate.from(record.getDate()))
                    .build();
            recordResponses.add(recordResponse);
        }

        return recordResponses;
    }
}
