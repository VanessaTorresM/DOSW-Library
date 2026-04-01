package edu.eci.dosw.DOSW_Library;


import edu.eci.dosw.DOSW_Library.Modelo.Book;
import edu.eci.dosw.DOSW_Library.Modelo.loan;
import edu.eci.dosw.DOSW_Library.Modelo.User;
import edu.eci.dosw.DOSW_Library.Exception.LibraryException;
import edu.eci.dosw.DOSW_Library.Validator.LoanValidator;
import org.junit.jupiter.api.Test;

import java.util.Date;
import java.time.LocalDate;
import static org.junit.jupiter.api.Assertions.*;

class LoanValidatorTest {

    private final LoanValidator validator = new LoanValidator();

    @Test
    void shouldPassWhenLoanIsComplete() {
        loan loan = new loan();
        loan.setBook(new Book());
        loan.setUser(new User());
        loan.setLoandate(new Date());

        assertDoesNotThrow(() -> validator.validateLoan(loan));
    }

    @Test
    void shouldThrowExceptionWhenBookIsNull() {
        loan loan = new loan();
        loan.setUser(new User());
        loan.setLoandate((new Date()));
        // loan.setBook(null);

        LibraryException exception = assertThrows(LibraryException.class,
                () -> validator.validateLoan(loan));
        assertEquals("El libro no puede ser nulo para un préstamo.", exception.getMessage());
    }

    @Test
    void shouldThrowExceptionWhenUserIsNull() {
        loan loan = new loan();
        loan.setBook(new Book());
        loan.setLoandate(new Date());

        LibraryException exception = assertThrows(LibraryException.class,
                () -> validator.validateLoan(loan));
        assertEquals("El usuario no puede ser nulo para un préstamo.", exception.getMessage());
    }

    @Test
    void shouldThrowExceptionWhenDateIsNull() {
        loan loan = new loan();
        loan.setBook(new Book());
        loan.setUser(new User());

        LibraryException exception = assertThrows(LibraryException.class,
                () -> validator.validateLoan(loan));
        assertEquals("La fecha de préstamo es obligatoria.", exception.getMessage());
    }
}
