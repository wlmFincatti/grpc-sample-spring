package com.example.grpcclient.endpoint.exception;

import com.example.grpcclient.external.gateway.excpetion.AccountNotFound;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

@RestControllerAdvice
public class AccountExceptionHandler {

    @ExceptionHandler(AccountNotFound.class)
    public ResponseEntity<?> error(AccountNotFound ex, WebRequest request) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }
}
