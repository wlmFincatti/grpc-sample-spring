package com.example.grpcserver.account.unit.usecase;

import com.example.grpcserver.account.domain.Account;
import com.example.grpcserver.account.fixtures.AccountFixture;
import com.example.grpcserver.account.usecase.GetAccountById;
import com.example.grpcserver.account.usecase.port.AccountGateway;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;

class GetAccountByIdTest {
    private AccountGateway accountGateway;
    private GetAccountById getAccountById;

    @BeforeEach
    public void setup() {
        accountGateway = mock(AccountGateway.class);
        getAccountById = new GetAccountById(accountGateway);
    }

    @Test
    void shouldGetAccountById() {
        //Given
        Integer id = 1;
        when(accountGateway.getAccountById(id)).thenReturn(AccountFixture.gimmeAccount(id));

        //When
        final Account result = getAccountById.execute(id);

        //Then
        verify(accountGateway, times(1)).getAccountById(id);
        assertEquals(1, result.getIdAccount());
    }
}