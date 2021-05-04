package com.example.grpcserver.account.usecase;

import com.example.grpcserver.account.domain.Account;
import com.example.grpcserver.account.usecase.port.AccountGateway;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class GetAccount {

    private AccountGateway accountGateway;

    public GetAccount(AccountGateway accountGateway) {
        this.accountGateway = accountGateway;
    }

    public List<Account> execute() {
        log.info("retrieve accounts");
        return accountGateway.getAccounts();
    }
}
