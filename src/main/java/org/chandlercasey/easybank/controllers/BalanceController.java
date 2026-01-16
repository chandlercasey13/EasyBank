package org.chandlercasey.easybank.controllers;

import lombok.RequiredArgsConstructor;
import org.chandlercasey.easybank.entities.AccountTransaction;
import org.chandlercasey.easybank.repositories.AccountTransactionsRepository;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class BalanceController {
    private final AccountTransactionsRepository accountTransactionsRepository;

    @GetMapping("/myBalance")
    public List<AccountTransaction> getBalanceDetails(Authentication authentication){
        long id = (long) authentication.getDetails();
        List<AccountTransaction> accountTransactions = accountTransactionsRepository.findByCustomerIdOrderByTransactionDtDesc(id);

        return accountTransactions;
    }
}

