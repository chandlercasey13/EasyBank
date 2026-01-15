package org.chandlercasey.easybank.controllers;

import lombok.RequiredArgsConstructor;
import org.chandlercasey.easybank.entities.Accounts;
import org.chandlercasey.easybank.entities.Cards;
import org.chandlercasey.easybank.repositories.CardsRepository;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class CardsController {

    private final CardsRepository cardsRepository;
    @GetMapping("/myCards")
    public List<Cards> getCardsDetails(Authentication authentication){
        long id = (long) authentication.getDetails();
        return cardsRepository.findByCustomerId(id);
        }


    }


