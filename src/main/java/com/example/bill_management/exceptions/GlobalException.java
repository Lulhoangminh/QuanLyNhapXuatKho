package com.example.bill_management.exceptions;

import com.example.bill_management.dto.responses.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.swing.text.html.parser.Entity;

@ControllerAdvice
public class GlobalException {
    @ExceptionHandler(value = AppException.class)
    public ResponseEntity<ApiResponse<?>> handlingAppException(AppException exception){
        IErrorCode iErrorCode = exception.getIErrorCode();
        ApiResponse<?> response = ApiResponse.builder()
                .code(iErrorCode.getCode())
                .message(iErrorCode.getMessage())
                .build();
        return ResponseEntity.status(iErrorCode.getHttpStatusCode()).body(response);
    }
    @ExceptionHandler(value = IllegalArgumentException.class)
    public ResponseEntity<ApiResponse<?>> handlingIllegalArgumentException(IllegalArgumentException exception){
        ApiResponse<?> response = ApiResponse.builder()
                .message(exception.getMessage())
                .build();
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }
}
