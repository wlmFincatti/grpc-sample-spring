package com.example.grpcclient.usecase;

import com.example.grpcclient.domain.Account;
import com.example.grpcclient.usecase.port.AccountGateway;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GetAccounts {

    private AccountGateway accountGateway;

    public GetAccounts(AccountGateway accountGateway) {
        this.accountGateway = accountGateway;
    }

    public List<Account> execute() {
        return accountGateway.getAccounts();
    }
}
