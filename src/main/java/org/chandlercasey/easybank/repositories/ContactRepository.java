package org.chandlercasey.easybank.repositories;

import org.chandlercasey.easybank.entities.Contact;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContactRepository extends CrudRepository<Contact, String> {


}