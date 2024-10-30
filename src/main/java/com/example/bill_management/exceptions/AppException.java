package com.example.bill_management.exceptions;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.antlr.v4.runtime.misc.NotNull;

@Getter
@Setter
@NoArgsConstructor
public class AppException extends RuntimeException{
    private IErrorCode iErrorCode;

    public AppException(IErrorCode errorCode){
        super(errorCode.getMessage());
        this.iErrorCode = errorCode;
    }

    public AppException(IErrorCode errorCode, Throwable cause){
        super(errorCode.getMessage(), cause);
        this.iErrorCode = errorCode;
    }
}
