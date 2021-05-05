package com.example.grpcserver.account.usecase;

import com.example.grpcserver.account.domain.Account;
import com.example.grpcserver.account.usecase.port.AccountGateway;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class GetAccountById {

    private AccountGateway accountGateway;

    public GetAccountById(AccountGateway accountGateway) {
        this.accountGateway = accountGateway;
    }

    public Account execute(Integer id) {
        log.info("retrieve accounts by id {}", id);
        return accountGateway.getAccountById(id);
    }
}
