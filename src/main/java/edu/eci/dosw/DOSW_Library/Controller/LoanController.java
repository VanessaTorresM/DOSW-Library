package edu.eci.dosw.DOSW_Library.Controller;

import edu.eci.dosw.DOSW_Library.Modelo.loan;
import edu.eci.dosw.DOSW_Library.Service.LoanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/loans")
public class LoanController {

    @Autowired
    private LoanService loanService;

    @PostMapping
    public loan createLoan(@RequestParam String userId, @RequestParam String bookId) {
        return loanService.borrowBook(userId, bookId);
    }

    @GetMapping
    public List<loan> getAllLoans() {
        return loanService.findAll();
    }

    @PutMapping("/{loanId}/return")
    public loan returnBook(@PathVariable String loanId) {
        return loanService.returnBook(loanId);
    }
}
