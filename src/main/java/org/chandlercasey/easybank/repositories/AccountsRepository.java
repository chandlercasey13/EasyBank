package org.chandlercasey.easybank.repositories;

import org.chandlercasey.easybank.entities.Account;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AccountsRepository extends CrudRepository<Account, Long> {

    List<Account> findByCustomerId(long customerId);

    Optional<Account> findByAccountNumberAndCustomerId(long accountNumber, long customerId);
}