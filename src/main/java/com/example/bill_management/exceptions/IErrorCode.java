package com.example.bill_management.exceptions;

import org.springframework.http.HttpStatusCode;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;

public interface IErrorCode {
    int getCode();
    String getMessage();
    HttpStatusCode getHttpStatusCode();
}
