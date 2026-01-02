package org.chandlercasey.easybank.controllers;

import lombok.RequiredArgsConstructor;
import org.chandlercasey.easybank.entities.Cards;
import org.chandlercasey.easybank.repositories.CardsRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class CardsController {

    private final CardsRepository cardsRepository;
    @GetMapping("/myCards")
    public List<Cards> getCardsDetails(@RequestParam long id){
        List<Cards> cardDetails = cardsRepository.findByCustomerId(id);

        if(cardDetails!=null){
            return cardDetails;
        } else {
            return null;
        }

    }
}

