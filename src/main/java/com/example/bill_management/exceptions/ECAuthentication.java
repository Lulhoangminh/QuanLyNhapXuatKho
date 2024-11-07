package com.example.bill_management.exceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public enum ECAuthentication implements IErrorCode{
    // Error mention authentication: Variable of error code(code, message - describe detail of error, http status code)
    UNAUTHENTICATED(1001, "Unauthenticated", HttpStatus.UNAUTHORIZED),
    UNAUTHORIZED(1002, "Unauthorized", HttpStatus.FORBIDDEN),
    ;
    private int code;
    private String message;
    private HttpStatusCode httpStatusCode;
}
