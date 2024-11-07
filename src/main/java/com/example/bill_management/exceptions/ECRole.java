package com.example.bill_management.exceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public enum ECRole implements IErrorCode{
    NONEXISTENT_ROLE(2001, "Role is not existed", HttpStatus.BAD_REQUEST),
    ;
    private int code;
    private String message;
    private HttpStatusCode httpStatusCode;
}
