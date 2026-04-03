package edu.eci.dosw.DOSW_Library.Validator;


import edu.eci.dosw.DOSW_Library.Modelo.loan;
import edu.eci.dosw.DOSW_Library.Exception.LibraryException;
import org.springframework.stereotype.Component;
@Component
public class LoanValidator {

    public  void validateLoan(loan loan) {
        if (loan.getBook() == null) {
            throw new LibraryException("El libro no puede ser nulo para un préstamo.");
        }
        if (loan.getUser() == null) {
            throw new LibraryException("El usuario no puede ser nulo para un préstamo.");
        }
        if (loan.getLoandate() == null) {
            throw new LibraryException("La fecha de préstamo es obligatoria.");
        }
    }
}

