package org.chandlercasey.easybank.repositories;


import org.chandlercasey.easybank.entities.Authority;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;



@Repository
public interface AuthorityRepository extends CrudRepository<Authority, Long> {
}
