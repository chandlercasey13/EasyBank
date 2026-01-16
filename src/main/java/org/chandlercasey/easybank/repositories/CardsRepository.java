package org.chandlercasey.easybank.repositories;


import org.chandlercasey.easybank.entities.Card;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CardsRepository extends CrudRepository<Card, Long> {

    List<Card> findByCustomerId(long customerId);
    Optional<Card> findByCardIdAndCustomerId(long cardId, long customerId);


}