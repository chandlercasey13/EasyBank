package org.chandlercasey.easybank.entities.dto;

public record CardResponse(
        Long cardId,
        String cardNumber,
        CardType cardType,
        int totalLimit,
        int amountUsed,
        int availableAmount,
        Long accountNumber
) {}

