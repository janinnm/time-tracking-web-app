package com.example.backend.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@Builder
public class RecordResponse {

    private Long recordId;
    private Long userId;
    private String username; // assuming you want to show username
    private Long categoryId;
    private String categoryName; // assuming you want to show category name
    private String title;
    private Integer hours;
    private LocalDate date; // only date, without time
}
