package org.chandlercasey.easybank.controllers;

import jakarta.persistence.Column;
import jakarta.persistence.Id;
import lombok.RequiredArgsConstructor;
import org.chandlercasey.easybank.entities.Account;
import org.chandlercasey.easybank.entities.Card;
import org.chandlercasey.easybank.entities.dto.AccountResponse;
import org.chandlercasey.easybank.entities.dto.CardResponse;
import org.chandlercasey.easybank.entities.dto.CreateCardRequest;
import org.chandlercasey.easybank.repositories.AccountsRepository;
import org.chandlercasey.easybank.repositories.CardsRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.security.SecureRandom;
import java.sql.Date;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class CardsController {

    private final CardsRepository cardsRepository;
    private final AccountsRepository accountRepository;


    @PostMapping("/myCards")
    public ResponseEntity<CardResponse> createCard(
            @RequestBody CreateCardRequest createCardRequest,
            Authentication authentication) {

        long customerId = (long) authentication.getDetails();

        Account account = accountRepository
                .findByAccountNumberAndCustomerId(
                        createCardRequest.accountNumber(),
                        customerId
                )
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Account not found"));

        Card card = new Card();
        card.setCustomerId(customerId);
        card.setCardNumber(generateCardNumber());
        card.setCardType(createCardRequest.cardType());
        card.setTotalLimit(500);
        card.setAmountUsed(0);
        card.setAvailableAmount(500);
        card.setAccount(account);
        card.setCreateDt(new Date(System.currentTimeMillis()));

        Card saved = cardsRepository.save(card);

        CardResponse response = new CardResponse(
                saved.getCardId(),
                saved.getCardNumber(),
                saved.getCardType(),
                saved.getTotalLimit(),
                saved.getAmountUsed(),
                saved.getAvailableAmount(),
                saved.getAccount().getAccountNumber()
        );

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }


    private String generateCardNumber() {
        SecureRandom random = new SecureRandom();
        StringBuilder sb = new StringBuilder(16);
        for (int i = 0; i < 16; i++) {
            sb.append(random.nextInt(10));
        }
        return sb.toString();
    }

    @DeleteMapping("/myCards")
    public ResponseEntity<Void> deleteCard(
            @RequestParam long cardId,
            Authentication authentication) {

        long customerId = (long) authentication.getDetails();

        Card card = cardsRepository
                .findByCardIdAndCustomerId(cardId, customerId)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Card not found"));

        cardsRepository.delete(card);
        return ResponseEntity.noContent().build();
    }


    @GetMapping("/myCards")
    public ResponseEntity<List<CardResponse>> getCardsDetails(Authentication authentication){

            long customerId = (long) authentication.getDetails();

            List<CardResponse> cards = cardsRepository.findByCustomerId(customerId)
                    .stream()
                    .map(c -> new CardResponse(
                            c.getCardId(),
                            c.getCardNumber(),
                            c.getCardType(),
                            c.getTotalLimit(),
                            c.getAmountUsed(),
                            c.getAvailableAmount(),
                            c.getAccount().getAccountNumber()
                    ))
                    .toList();

            return ResponseEntity.ok(cards);
        }


        }







