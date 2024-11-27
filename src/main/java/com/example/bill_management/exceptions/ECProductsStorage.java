package com.example.bill_management.exceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public enum ECProductsStorage implements IErrorCode{
    EXISTENT_PRODUCT_ID(3001, "Product's id is existed", HttpStatus.BAD_REQUEST),
    NONEXISTENT_PRODUCT_ID(3002, "Product's id is not existed", HttpStatus.BAD_REQUEST),
    EXISTENT_STORAGE_ID(3003, "Storage's id is existed", HttpStatus.BAD_REQUEST),
    NONEXISTENT_STORAGE_ID(3004, "Storage's id is not existed", HttpStatus.BAD_REQUEST),
    PRODUCT_IS_NOT_IN_STORAGE(3005, "Product is not in storage", HttpStatus.BAD_REQUEST),

    ;
    private int code;
    private String message;
    private HttpStatusCode httpStatusCode;
}