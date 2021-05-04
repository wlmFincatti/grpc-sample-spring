package com.example.grpcclient.usecase;

import com.example.grpcclient.domain.Account;
import com.example.grpcclient.usecase.port.AccountGateway;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GetAccountById {

    private AccountGateway accountGateway;

    public GetAccountById(AccountGateway accountGateway) {
        this.accountGateway = accountGateway;
    }

    public Account execute(Integer id) {
        return accountGateway.getAccountById(id);
    }
}
