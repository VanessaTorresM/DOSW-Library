package edu.eci.dosw.DOSW_Library.Controller;

import edu.eci.dosw.DOSW_Library.Modelo.loan;
import edu.eci.dosw.DOSW_Library.Persistence.Entidades.LoanEntity;
import edu.eci.dosw.DOSW_Library.Persistence.Mapper.LoanMapper;
import edu.eci.dosw.DOSW_Library.Service.LoanService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/loans")
@Tag(name = "Préstamos", description = "Registro y consulta de préstamos de libros")
public class LoanController {

    private final LoanService loanService;
    private final LoanMapper loanMapper;

    public LoanController(LoanService loanService, LoanMapper loanMapper) {
        this.loanService = loanService;
        this.loanMapper = loanMapper;
    }

    @PostMapping
    @Operation(summary = "Crear un nuevo préstamo")
    public loan createLoan(@RequestParam String userId, @RequestParam String bookId) {
        LoanEntity newLoan = loanService.createLoan(userId, bookId);
        return loanMapper.toModel(newLoan);
    }

    @GetMapping
    @Operation(summary = "Historial de préstamos")
    public List<loan> getAllLoans() {
        return loanService.getAllLoans().stream()
                .map(loanMapper::toModel)
                .collect(Collectors.toList());
    }
}