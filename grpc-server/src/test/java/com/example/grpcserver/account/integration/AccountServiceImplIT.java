package com.example.grpcserver.account.integration;

import com.example.account.AccountResponse;
import com.example.account.AccountServiceGrpc;
import com.example.account.EmptyRequest;
import com.example.grpcserver.account.entrypoint.AccountServiceImpl;
import com.example.grpcserver.account.fixtures.AccountFixture;
import com.example.grpcserver.account.usecase.GetAccount;
import com.example.grpcserver.account.usecase.GetAccountById;
import com.github.tomakehurst.wiremock.WireMockServer;
import io.grpc.inprocess.InProcessChannelBuilder;
import io.grpc.inprocess.InProcessServerBuilder;
import io.grpc.testing.GrpcCleanupRule;
import org.junit.Ignore;
import org.junit.Rule;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;

import java.io.IOException;
import java.util.Iterator;

import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static org.mockito.Mockito.when;

@ActiveProfiles("it")
@SpringBootTest
public class AccountServiceImplIT {

    @Autowired
    private GetAccount getAccount;
    @Autowired
    private GetAccountById getAccountById;

    @Rule
    public final GrpcCleanupRule grpcCleanup = new GrpcCleanupRule();

    private WireMockServer wireMockServer = new WireMockServer(8889);

    @BeforeEach
    public void setup() {
        configureFor("localhost", 8889);
        wireMockServer.start();
        stubFor(get("/account").willReturn(aResponse()
                .withStatus(HttpStatus.OK.value())
                .withHeader("Content-Type", MediaType.APPLICATION_JSON_VALUE)
                .withBodyFile("account.json")));
    }

    @AfterEach
    public void teardown() {
        wireMockServer.stop();
    }

    @Ignore
    @Test
    public void shouldReturnAccounts() throws IOException {
        when(getAccount.execute()).thenReturn(AccountFixture.gimmeAccounts());
        // Generate a unique in-process server name.
        String serverName = InProcessServerBuilder.generateName();

        // Create a server, add service, start, and register for automatic graceful shutdown.
        grpcCleanup.register(InProcessServerBuilder
                .forName(serverName).directExecutor().addService(new AccountServiceImpl(getAccount, getAccountById)).build()).start();

        AccountServiceGrpc.AccountServiceBlockingStub blockingStub = AccountServiceGrpc.newBlockingStub(
                // Create a client channel and register for automatic graceful shutdown.
                grpcCleanup.register(InProcessChannelBuilder.forName(serverName).directExecutor().build()));
        final Iterator<AccountResponse> accounsResult = blockingStub.retrieveAccount(EmptyRequest.newBuilder().build());
    }
}
