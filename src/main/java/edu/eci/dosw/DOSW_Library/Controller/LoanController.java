package edu.eci.dosw.DOSW_Library.Controller;

import edu.eci.dosw.DOSW_Library.Modelo.Loan;
import edu.eci.dosw.DOSW_Library.Persistence.Entidades.LoanEntity;
import edu.eci.dosw.DOSW_Library.Persistence.Mapper.LoanMapper;
import edu.eci.dosw.DOSW_Library.Service.LoanService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/loans")
@Tag(name = "Préstamos", description = "Registro y consulta de préstamos")
public class LoanController {

    private final LoanService loanService;
    private final LoanMapper loanMapper;

    public LoanController(LoanService loanService, LoanMapper loanMapper) {
        this.loanService = loanService;
        this.loanMapper = loanMapper;
    }

    @PostMapping
    @PreAuthorize("hasAnyRole('USER', 'LIBRARIAN', 'ADMIN')")
    public Loan createLoan(@RequestParam String userId, @RequestParam String bookId) {
        LoanEntity newLoan = loanService.createLoan(userId, bookId);
        return loanMapper.toModel(newLoan);
    }

    @GetMapping
    @PreAuthorize("hasAnyRole('LIBRARIAN', 'ADMIN')")
    public List<Loan> getAllLoans() {
        return loanService.getAllLoans().stream()
                .map(loanMapper::toModel)
                .collect(Collectors.toList());
    }

    @GetMapping("/user/{userId}")
    @PreAuthorize("#userId == authentication.name or hasAnyRole('LIBRARIAN', 'ADMIN')")
    public List<Loan> getLoansByUserId(@PathVariable String userId) {
        return loanService.getLoansByUserId(userId).stream()
                .map(loanMapper::toModel)
                .collect(Collectors.toList());
    }

    @PutMapping("/{loanId}/return")
    @PreAuthorize("hasAnyRole('USER', 'LIBRARIAN', 'ADMIN')")
    public Loan returnBook(@PathVariable Long loanId) {
        LoanEntity returnedLoan = loanService.returnLoan(loanId);
        return loanMapper.toModel(returnedLoan);
    }
}