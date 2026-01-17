package org.chandlercasey.easybank.controllers;

import lombok.RequiredArgsConstructor;
import org.chandlercasey.easybank.entities.AccountTransaction;
import org.chandlercasey.easybank.entities.dto.AccountTransactionResponse;
import org.chandlercasey.easybank.repositories.AccountTransactionsRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class BalanceController {
    private final AccountTransactionsRepository accountTransactionsRepository;

    @GetMapping("/myBalance")
    public ResponseEntity<List<AccountTransactionResponse>> getBalanceDetails(
            Authentication authentication) {

        long customerId = (long) authentication.getDetails();

        List<AccountTransactionResponse> transactions =
                accountTransactionsRepository
                        .findByCustomerIdOrderByTransactionDtDesc(customerId)
                        .stream()
                        .map(t -> new AccountTransactionResponse(
                                t.getTransactionId(),
                                t.getAccountNumber(),
                                t.getTransactionDt(),
                                t.getTransactionSummary(),
                                t.getTransactionType(),
                                t.getTransactionAmt(),
                                t.getClosingBalance(),
                                t.getCard() != null ? t.getCard().getCardNumber() : null
                        ))
                        .toList();

        return ResponseEntity.ok(transactions);
    }

}

