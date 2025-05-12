package com.example.backend.model;

import com.example.backend.enums.ResultCode;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
public class ResponseVO {

    private final int code;

    private final String message;

    @JsonIgnore
    private final ResultCode resultCode;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private final Object data;

    public ResponseVO(ResultCode resultCode, Object data) {
        this.code = resultCode.getCode();
        this.message = resultCode.getMessage();
        this.resultCode = resultCode;
        this.data = data;
    }

    public ResponseVO(ResultCode resultCode) {
        this(resultCode, null);
    }
}
