package com.example.grpcclient.adapter;

import com.example.account.AccountResponse;
import com.example.grpcclient.domain.Account;

public class AccountResponseToDomain {
    public static Account convert(AccountResponse accountResponse) {
        return Account.builder()
                .idAccount(accountResponse.getId())
                .brand(accountResponse.getBrand())
                .build();
    }
}
