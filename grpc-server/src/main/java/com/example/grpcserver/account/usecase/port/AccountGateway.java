package com.example.grpcserver.account.usecase.port;

import com.example.grpcserver.account.domain.Account;

import java.util.List;

public interface AccountGateway {

    List<Account> getAccounts();

    Account getAccountById(Integer id);
}
