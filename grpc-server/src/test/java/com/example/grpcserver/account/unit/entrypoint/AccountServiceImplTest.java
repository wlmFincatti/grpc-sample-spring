package com.example.grpcserver.account.unit.usecase.entrypoint;

import com.example.account.AccountResponse;
import com.example.account.EmptyRequest;
import com.example.grpcserver.account.entrypoint.AccountServiceImpl;
import com.example.grpcserver.account.fixtures.AccountFixture;
import com.example.grpcserver.account.usecase.GetAccount;
import com.example.grpcserver.account.usecase.GetAccountById;
import io.grpc.internal.testing.StreamRecorder;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class AccountServiceImplTest {

    private AccountServiceImpl myService;
    private GetAccount getAccount;
    private GetAccountById getAccountById;

    @BeforeEach
    public void setup() {
        getAccount = mock(GetAccount.class);
        getAccountById = mock(GetAccountById.class);
        myService = new AccountServiceImpl(getAccount, getAccountById);
    }

    @Test
    void shouldGetAccounts() {
        //Given
        StreamRecorder<AccountResponse> responseObserver = StreamRecorder.create();
        when(getAccount.execute()).thenReturn(AccountFixture.gimmeAccounts());

        //When
        myService.retrieveAccount(EmptyRequest.newBuilder().build(), responseObserver);

        //Then
        final List<AccountResponse> accounts = responseObserver.getValues();
        verify(getAccount, times(1)).execute();
        assertEquals(1, accounts.size());
    }

}