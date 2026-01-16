package org.chandlercasey.easybank.controllers;

import lombok.RequiredArgsConstructor;
import org.chandlercasey.easybank.entities.AccountTypes;
import org.chandlercasey.easybank.entities.Accounts;
import org.chandlercasey.easybank.entities.CreateAccountRequestDTO;
import org.chandlercasey.easybank.repositories.AccountsRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import java.sql.Date;

@RestController
@RequiredArgsConstructor
public class AccountController {
    private final AccountsRepository accountsRepository;

    @PostMapping("/myAccount")
    public ResponseEntity<Accounts> createAccount(@RequestBody CreateAccountRequestDTO createAccountRequest, Authentication authentication) {

        long userId = (long) authentication.getDetails();
        Accounts account = new Accounts();
        account.setAccountType(createAccountRequest.accountType());
        account.setBranchAddress("123 Main St, Austin, TX");
        account.setCustomerId(userId);
        account.setCreateDt(new Date(System.currentTimeMillis()));


        Accounts savedAccount = accountsRepository.save(account);
            return ResponseEntity.ok().body(savedAccount);
    }

    @GetMapping("/myAccount")
    public Accounts getAccountDetails(Authentication authentication) {
        long id = (long) authentication.getDetails();
        return accountsRepository.findByCustomerId(id);
    }
}

