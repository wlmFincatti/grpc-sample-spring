package com.example.grpcclient.endpoint;

import com.example.grpcclient.domain.Account;
import com.example.grpcclient.external.gateway.excpetion.AccountNotFound;
import com.example.grpcclient.usecase.GetAccountById;
import com.example.grpcclient.usecase.GetAccounts;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("account")
public class AccountController {

    private GetAccounts getAccounts;
    private GetAccountById getAccountById;

    public AccountController(GetAccounts getAccounts, GetAccountById getAccountById) {
        this.getAccounts = getAccounts;
        this.getAccountById = getAccountById;
    }

    @GetMapping
    public ResponseEntity<List<Account>> getAccounts() {
        return ResponseEntity.ok(getAccounts.execute());
    }

    @GetMapping("/{idAccount}")
    public ResponseEntity<Account> getAccounts(@PathVariable Integer idAccount) {
        return ResponseEntity.ok(getAccountById.execute(idAccount));
    }
}
