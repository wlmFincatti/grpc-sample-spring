package com.example.grpcclient.external.gateway.excpetion;

public class AccountNotFound extends RuntimeException{

    public AccountNotFound(String message) {
        super(message);
    }
}
