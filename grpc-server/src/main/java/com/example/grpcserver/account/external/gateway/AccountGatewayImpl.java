package com.example.grpcserver.account.external.gateway;

import com.example.grpcserver.account.config.AccountGatewayConfig;
import com.example.grpcserver.account.domain.Account;
import com.example.grpcserver.account.usecase.port.AccountGateway;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Slf4j
@Component
public class AccountGatewayImpl implements AccountGateway {

    private RestTemplate restTemplate;
    private AccountGatewayConfig config;

    public AccountGatewayImpl(@Qualifier("shortTimeout") RestTemplate restTemplate, AccountGatewayConfig config) {
        this.restTemplate = restTemplate;
        this.config = config;
    }

    @Override
    public List<Account> getAccounts() {

        final ResponseEntity<List<Account>> result = restTemplate.exchange(config.getFormatedUrl(), HttpMethod.GET, null, new ParameterizedTypeReference<List<Account>>() {
        });

        return result.getBody();
    }

    @Override
    public Account getAccountById(Integer id) {
        final ResponseEntity<Account> account = restTemplate.getForEntity(config.getFormatedUrlById(id), Account.class);

        return account.getBody();
    }

}
