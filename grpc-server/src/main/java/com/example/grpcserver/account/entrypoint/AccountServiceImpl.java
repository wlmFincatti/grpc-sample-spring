package com.example.grpcserver.account.entrypoint;

import com.example.account.AccountId;
import com.example.account.AccountResponse;
import com.example.account.AccountServiceGrpc;
import com.example.account.EmptyRequest;
import com.example.grpcserver.account.domain.Account;
import com.example.grpcserver.account.usecase.GetAccount;
import com.example.grpcserver.account.usecase.GetAccountById;
import io.grpc.Status;
import io.grpc.stub.StreamObserver;
import lombok.extern.slf4j.Slf4j;
import net.devh.boot.grpc.server.service.GrpcService;

import java.util.List;

@Slf4j
@GrpcService
public class AccountServiceImpl extends AccountServiceGrpc.AccountServiceImplBase {

    private GetAccount getAccount;
    private GetAccountById getAccountById;

    public AccountServiceImpl(GetAccount getAccount, GetAccountById getAccountById) {
        this.getAccount = getAccount;
        this.getAccountById = getAccountById;
    }

    @Override
    public void retrieveAccount(EmptyRequest request, StreamObserver<AccountResponse> responseObserver) {
        final List<Account> accounts = getAccount.execute();

        accounts.forEach(account -> {
            log.info("account retrieve {}", account.toString());
            responseObserver.onNext(responseAccount(account));
        });
        log.info("response accounts completed");
        responseObserver.onCompleted();
    }

    @Override
    public void retrieveAccountById(AccountId request, StreamObserver<AccountResponse> responseObserver) {
        try {
            final Account account = getAccountById.execute(request.getId());
            responseObserver.onNext(responseAccount(account));
            responseObserver.onCompleted();
        } catch (RuntimeException exception) {
            responseObserver.onError(Status.NOT_FOUND.withDescription(exception.getMessage()).asRuntimeException());
        }
    }

    private AccountResponse responseAccount(Account account) {
        return AccountResponse.newBuilder()
                .setBrand(account.getBrand())
                .setId(account.getIdAccount())
                .build();
    }
}
