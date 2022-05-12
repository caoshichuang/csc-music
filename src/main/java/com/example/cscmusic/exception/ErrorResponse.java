package com.example.cscmusic.exception;

import lombok.Data;

@Data
public class ErrorResponse {
    private Integer code; // 错误码

    private String message; // 错误信息

    private Object trace; //错误路径
}
