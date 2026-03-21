package edu.eci.dosw.DOSW_Library.Controller;

import edu.eci.dosw.DOSW_Library.Modelo.loan;
import edu.eci.dosw.DOSW_Library.Service.LoanService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/loans")
public class LoanController {

    private final LoanService loanService;

    public LoanController(LoanService loanService) {
        this.loanService = loanService;
    }

    @PostMapping
    public loan createLoan(@RequestParam String userId, @RequestParam String bookId) {
        return loanService.createLoan(userId, bookId);
    }

    @GetMapping
    public List<loan> getAllLoans() {
        return loanService.getAllLoans();
    }
}
