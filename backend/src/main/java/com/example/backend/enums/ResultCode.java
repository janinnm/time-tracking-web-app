package com.example.backend.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum ResultCode {
    RSC_2000(HttpStatus.OK, 2000, "OK"),
    RSC_2010(HttpStatus.CREATED, 2010, "Created"),
    RSC_2020(HttpStatus.ACCEPTED, 2020, "Accepted"),
    RSC_2040(HttpStatus.NO_CONTENT, 2040, "Deleted"),
    RSC_4000(HttpStatus.BAD_REQUEST, 4000, "Invalid Request");

    private final HttpStatus httpStatus;
    private final int code;
    private final String message;
}
