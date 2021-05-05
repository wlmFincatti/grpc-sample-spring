package com.example.grpcserver.account.fixtures;

import com.example.grpcserver.account.domain.Account;

import java.util.Arrays;
import java.util.List;

public class AccountFixture {

    public static Account gimmeAccount(Integer id) {
        return Account.builder().idAccount(id).agency(1252).brand("Other").digit(2).number(1244).build();
    }

    public static List<Account> gimmeAccounts() {
        return Arrays.asList(gimmeAccount(1));
    }
}
