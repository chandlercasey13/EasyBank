package org.chandlercasey.easybank.controllers;

import lombok.RequiredArgsConstructor;
import org.chandlercasey.easybank.entities.Authority;
import org.chandlercasey.easybank.entities.Customer;
import org.chandlercasey.easybank.repositories.AuthorityRepository;
import org.chandlercasey.easybank.repositories.CustomerRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Date;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
public class UserController {
    private final CustomerRepository customerRepository;
    private final AuthorityRepository authorityRepository;
    private final PasswordEncoder passwordEncoder;

    @PostMapping("/register")
    public ResponseEntity<String> registerUser(@RequestBody Customer customer){
        try{ System.out.println(customer.getMobileNumber());
            String hashPwd = passwordEncoder.encode(customer.getPwd());
            customer.setPwd(hashPwd);
            customer.setCreateDt(new Date(System.currentTimeMillis()));
            Customer savedCustomer = customerRepository.save(customer);


            Authority authority = new Authority();
            authority.setCustomer(savedCustomer);
            authority.setName("ROLE_USER");
            authorityRepository.save(authority);

            if(savedCustomer.getId()>0) {
                return ResponseEntity.status(HttpStatus.CREATED).
                        body("User details are successfully registered");
            } else {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).
                        body("User registration failed");
            }


        }catch (Exception ex){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("An exception occured" + ex.getMessage());
        }

    }

    // spring security will automatically try to authenticate
    @RequestMapping("/user")
    public Customer getUserDetailsAfterLogin(Authentication authentication){
        Optional<Customer> optionalCustomer = customerRepository.findByEmail(authentication.getName());
        return optionalCustomer.orElse(null);
    }



}
