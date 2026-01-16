package org.chandlercasey.easybank.controllers;

import lombok.RequiredArgsConstructor;
import org.chandlercasey.easybank.entities.Account;
import org.chandlercasey.easybank.entities.dto.AccountResponse;
import org.chandlercasey.easybank.entities.dto.CreateAccountRequest;
import org.chandlercasey.easybank.repositories.AccountsRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.sql.Date;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class AccountController {
    private final AccountsRepository accountsRepository;

    @PostMapping("/myAccount")
    public ResponseEntity<Account> createAccount(@RequestBody CreateAccountRequest createAccountRequest, Authentication authentication) {

        long userId = (long) authentication.getDetails();
        Account account = new Account();
        account.setAccountType(createAccountRequest.accountType());
        account.setBranchAddress("123 Main St, Austin, TX");
        account.setCustomerId(userId);
        account.setCreateDt(new Date(System.currentTimeMillis()));


        Account savedAccount = accountsRepository.save(account);
            return ResponseEntity.ok().body(savedAccount);
    }

    @GetMapping("/myAccount")
    public ResponseEntity<List<AccountResponse>> getMyAccounts(Authentication authentication) {

        long customerId = (long) authentication.getDetails();

        List<AccountResponse> accounts = accountsRepository.findByCustomerId(customerId)
                .stream()
                .map(a -> new AccountResponse(
                        a.getAccountNumber(),
                        a.getAccountType(),
                        a.getBranchAddress(),
                        a.getCreateDt()
                ))
                .toList();

        return ResponseEntity.ok(accounts);
    }

    @DeleteMapping("/myAccount")
    public ResponseEntity<Void> deleteAccount(
            @RequestParam long accountNumber,
            Authentication authentication) {

        long userId = (long) authentication.getDetails();

        Account account = accountsRepository
                .findByAccountNumberAndCustomerId(accountNumber, userId)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Account not found"));

        accountsRepository.delete(account);
        return ResponseEntity.noContent().build();
    }

}

