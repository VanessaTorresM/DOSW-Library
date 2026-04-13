package edu.eci.dosw.DOSW_Library.Controller;

import edu.eci.dosw.DOSW_Library.Modelo.Loan;
import edu.eci.dosw.DOSW_Library.Persistence.nonrelational.Document.LoanMongoEntity;
import edu.eci.dosw.DOSW_Library.Persistence.nonrelational.Mapper.LoanMongoMapper;
// import edu.eci.dosw.DOSW_Library.Persistence.relational.Entidades.LoanEntity;
// import edu.eci.dosw.DOSW_Library.Persistence.relational.Mapper.LoanMapper;
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
    // private final LoanMapper loanMapper; // Relacional
    private final LoanMongoMapper loanMapper; // No Relacional

    public LoanController(LoanService loanService, LoanMongoMapper loanMapper) {
        this.loanService = loanService;
        this.loanMapper = loanMapper;
    }

    @PostMapping
    @PreAuthorize("hasAnyRole('USER', 'LIBRARIAN', 'ADMIN')")
    @Operation(summary = "Crear un nuevo préstamo")
    public Loan createLoan(@RequestParam String userId, @RequestParam String bookId) {
        LoanMongoEntity newLoan = loanService.createLoan(userId, bookId);
        return loanMapper.toModel(newLoan);
    }

    @GetMapping
    @PreAuthorize("hasAnyRole('LIBRARIAN', 'ADMIN')")
    @Operation(summary = "Obtener todos los préstamos")
    public List<Loan> getAllLoans() {
        return loanService.getAllLoans().stream()
                .map(loanMapper::toModel)
                .collect(Collectors.toList());
    }

    @GetMapping("/user/{userId}")
    @PreAuthorize("#userId == authentication.name or hasAnyRole('LIBRARIAN', 'ADMIN')")
    @Operation(summary = "Obtener préstamos por ID de usuario")
    public List<Loan> getLoansByUserId(@PathVariable String userId) {
        return loanService.getLoansByUserId(userId).stream()
                .map(loanMapper::toModel)
                .collect(Collectors.toList());
    }

    @PutMapping("/{loanId}/return")
    @PreAuthorize("hasAnyRole('USER', 'LIBRARIAN', 'ADMIN')")
    @Operation(summary = "Registrar la devolución de un libro")
    public Loan returnBook(@PathVariable String loanId) { // Cambiado de Long a String para Mongo
        LoanMongoEntity returnedLoan = loanService.returnLoan(loanId);
        return loanMapper.toModel(returnedLoan);
    }
}