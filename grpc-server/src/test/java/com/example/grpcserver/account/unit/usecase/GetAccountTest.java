package com.example.grpcserver.account.unit.usecase;

import com.example.grpcserver.account.domain.Account;
import com.example.grpcserver.account.fixtures.AccountFixture;
import com.example.grpcserver.account.usecase.GetAccount;
import com.example.grpcserver.account.usecase.port.AccountGateway;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class GetAccountTest {

    private AccountGateway accountGateway;
    private GetAccount getAccount;

    @BeforeEach
    public void setup() {
        accountGateway = mock(AccountGateway.class);
        getAccount = new GetAccount(accountGateway);
    }

    @Test
    void shouldGetAccounts() {
        //Given
        when(accountGateway.getAccounts()).thenReturn(AccountFixture.gimmeAccounts());

        //When
        final List<Account> result = getAccount.execute();

        //Then
        verify(accountGateway, times(1)).getAccounts();
        assertEquals(AccountFixture.gimmeAccounts(), result);
        assertEquals(1, result.size());
    }

}