package com.example.backend.exception;

import com.example.backend.enums.ResultCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class CustomErrorException extends RuntimeException {
    private final ResultCode resultCode;
}

