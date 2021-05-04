package com.example.grpcclient.usecase.port;


import com.example.grpcclient.domain.Account;

import java.util.List;

public interface AccountGateway {

    List<Account> getAccounts();

    Account getAccountById(Integer id);
}
