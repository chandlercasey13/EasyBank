package org.chandlercasey.easybank.controllers;

import lombok.RequiredArgsConstructor;
import org.chandlercasey.easybank.entities.Accounts;
import org.chandlercasey.easybank.repositories.AccountsRepository;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class AccountController {
    private final AccountsRepository accountsRepository;


    @GetMapping("/myAccount")
    public Accounts getAccountDetails(Authentication authentication) {
        long id = (long) authentication.getDetails();
        return accountsRepository.findByCustomerId(id);
    }
}

