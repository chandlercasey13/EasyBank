package org.chandlercasey.easybank.controllers;

import lombok.RequiredArgsConstructor;
import org.chandlercasey.easybank.entities.Loans;
import org.chandlercasey.easybank.repositories.LoanRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class LoansController {
    private final LoanRepository loanRepository;
    @GetMapping("/myLoans")
    public List<Loans> getLoansDetails(@RequestParam long id){
        List<Loans> loans = loanRepository.findByCustomerIdOrderByStartDtDesc(id);
        if (loans!=null){
            return loans;
        } else {
            return null;
        }
    }
}

