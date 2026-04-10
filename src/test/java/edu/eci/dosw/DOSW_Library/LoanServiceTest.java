package edu.eci.dosw.DOSW_Library;


import edu.eci.dosw.DOSW_Library.Persistence.relational.Entidades.BookEntity;
import edu.eci.dosw.DOSW_Library.Persistence.relational.Entidades.LoanEntity;
import edu.eci.dosw.DOSW_Library.Persistence.relational.Entidades.UserEntity;
import edu.eci.dosw.DOSW_Library.Persistence.relational.Mapper.LoanMapper;
import edu.eci.dosw.DOSW_Library.Persistence.relational.Repositorios.LoanRepository;
import edu.eci.dosw.DOSW_Library.Service.BookService;
import edu.eci.dosw.DOSW_Library.Service.LoanService;
import edu.eci.dosw.DOSW_Library.Service.UserService;
import edu.eci.dosw.DOSW_Library.Util.ValidationUtil;
import edu.eci.dosw.DOSW_Library.Validator.LoanValidator;
import edu.eci.dosw.DOSW_Library.Exception.BookNotAvailableException;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;



@ExtendWith(MockitoExtension.class)
class LoanServiceTest {

    @Mock private BookService bookService;
    @Mock private UserService userService;
    @Mock private LoanRepository loanRepository;
    @Mock private LoanMapper loanMapper;
    @Mock private ValidationUtil validationUtil;
    @Mock private LoanValidator loanValidator;

    @InjectMocks
    private LoanService loanService;

    @Test
    void shouldCreateLoanSuccessfullyWhenStockAvailable() {
        UserEntity userE = new UserEntity(); userE.setUserId("u1");
        BookEntity bookE = new BookEntity(); bookE.setId("b1"); bookE.setAvailableStock(1);

        when(userService.findEntityById("u1")).thenReturn(userE);
        when(bookService.findEntityById("b1")).thenReturn(bookE);

        LoanEntity savedLoan = new LoanEntity();
        savedLoan.setStatus("active");
        when(loanRepository.save(any(LoanEntity.class))).thenReturn(savedLoan);

        LoanEntity result = loanService.createLoan("u1", "b1");

        assertNotNull(result);
        assertEquals("active", result.getStatus());
        verify(bookService).saveEntity(any(BookEntity.class));
    }

    @Test
    void shouldFailLoanWhenNoStockIsAvailable() {
        UserEntity userE = new UserEntity();
        BookEntity bookE = new BookEntity(); bookE.setAvailableStock(0);

        when(userService.findEntityById("u1")).thenReturn(userE);
        when(bookService.findEntityById("b1")).thenReturn(bookE);

        doThrow(new BookNotAvailableException("No hay ejemplares disponibles"))
                .when(validationUtil).checkAvailability(0);

        assertThrows(BookNotAvailableException.class, () -> {
            loanService.createLoan("u1", "b1");
        });

        verify(loanRepository, never()).save(any());
    }

    @Test
    void shouldThrowExceptionWhenUserNotFound() {
        when(userService.findEntityById("u999")).thenReturn(null);

        assertThrows(RuntimeException.class, () -> {
            loanService.createLoan("u999", "b1");
        });
    }

    @Test
    void shouldReturnAllLoans() {
        when(loanRepository.findAll()).thenReturn(Collections.emptyList());

        List<LoanEntity> result = loanService.getAllLoans();

        assertNotNull(result);
        verify(loanRepository).findAll();
    }
}