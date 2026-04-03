package edu.eci.dosw.DOSW_Library.Controller;

import edu.eci.dosw.DOSW_Library.Modelo.loan;
import edu.eci.dosw.DOSW_Library.Service.LoanService;
import edu.eci.dosw.DOSW_Library.Validator.LoanValidator;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/loans")
@Tag(name = "Préstamos", description = "Registro y consulta de préstamos de libros")
public class LoanController {

    private final LoanService loanService;
    private final LoanValidator loanValidator;

    public LoanController(LoanService loanService, LoanValidator loanValidator) {
        this.loanService = loanService;
        this.loanValidator = loanValidator;
    }

    @PostMapping
    @Operation(summary = "Crear un nuevo préstamo")
    public loan createLoan(@RequestParam String userId, @RequestParam String bookId) {
        return loanService.createLoan(userId, bookId);
    }

    @GetMapping
    @Operation(summary = "Historial de préstamos")
    public List<loan> getAllLoans() {
        return loanService.getAllLoans();
    }
}
