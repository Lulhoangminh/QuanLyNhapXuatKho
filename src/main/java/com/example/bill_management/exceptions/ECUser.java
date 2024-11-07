package com.example.bill_management.exceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public enum ECUser implements IErrorCode{
    EXISTENT_USER(1001, "User is existed", HttpStatus.BAD_REQUEST),
    NONEXISTENT_USER(1002, "User is not existed", HttpStatus.BAD_REQUEST),
    ;
    private int code;
    private String message;
    private HttpStatusCode httpStatusCode;
}
