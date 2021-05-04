package com.example.grpcclient.external.gateway;

import com.example.account.AccountId;
import com.example.account.AccountResponse;
import com.example.account.AccountServiceGrpc;
import com.example.account.EmptyRequest;
import com.example.grpcclient.adapter.AccountResponseToDomain;
import com.example.grpcclient.domain.Account;
import com.example.grpcclient.external.gateway.excpetion.AccountNotFound;
import com.example.grpcclient.usecase.port.AccountGateway;
import io.grpc.StatusRuntimeException;
import lombok.extern.slf4j.Slf4j;
import net.devh.boot.grpc.client.inject.GrpcClient;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
public class AccountGatewayGRPCImpl implements AccountGateway {

    @GrpcClient("accountservice")
    private AccountServiceGrpc.AccountServiceBlockingStub accountClient;

    @Override
    public List<Account> getAccounts() {
        final ArrayList<Account> accounts = new ArrayList<>();
        accountClient.retrieveAccount(EmptyRequest.newBuilder().build())
                .forEachRemaining(account -> {
                    log.info("retrieve accounts from grps service {}", account);
                    accounts.add(AccountResponseToDomain.convert(account));
                });
        return accounts;
    }

    @Override
    public Account getAccountById(Integer id) {
        Account account = null;
        try {
            final AccountResponse accountResponse = accountClient.retrieveAccountById(AccountId.newBuilder().setId(id).build());
            account = AccountResponseToDomain.convert(accountResponse);
        } catch (StatusRuntimeException ex) {
            throw new AccountNotFound(ex.getMessage());
        }
        return account;
    }
}
