package org.chandlercasey.easybank.entities.dto;

import java.util.Date;

public record AccountTransactionResponse(
        String transactionId,
        long accountNumber,
        Date transactionDt,
        String transactionSummary,
        String transactionType,
        int transactionAmt,
        int closingBalance,
        String cardNumber


) {}
