package edu.eci.dosw.DOSW_Library;

import edu.eci.dosw.DOSW_Library.Modelo.Book;
import edu.eci.dosw.DOSW_Library.Modelo.User;
import edu.eci.dosw.DOSW_Library.Modelo.loan;
import edu.eci.dosw.DOSW_Library.Service.BookService;
import edu.eci.dosw.DOSW_Library.Service.LoanService;
import edu.eci.dosw.DOSW_Library.Service.UserService;
import edu.eci.dosw.DOSW_Library.Util.ValidationUtil;
import edu.eci.dosw.DOSW_Library.Exeption.BookNotAvailableException;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class LoanServiceTest {

    @Mock
    private BookService bookService;
    @Mock
    private UserService userService;

    @Mock
    private ValidationUtil validationUtil;

    @InjectMocks
    private LoanService loanService;

    @Test
    void shouldCreateLoanSuccessfullyWhenStockAvailable() {
        User user = new User();
        user.setId("u1");
        Book book = new Book();
        book.setId("b1");

        when(userService.findById("u1")).thenReturn(user);
        when(bookService.findById("b1")).thenReturn(book);
        when(bookService.getStock(book)).thenReturn(1); // Hay stock

        loan result = loanService.createLoan("u1", "b1");

        assertNotNull(result);
        assertEquals("active", result.getStatus());
        assertEquals(book, result.getBook());

        verify(bookService, times(1)).updateStock(book, 0);
    }

    @Test
    void shouldFailLoanWhenNoStockIsAvailable() {
        User user = new User();
        user.setId("u1");
        Book book = new Book();
        book.setId("b1");

        when(userService.findById("u1")).thenReturn(user);
        when(bookService.findById("b1")).thenReturn(book);
        when(bookService.getStock(book)).thenReturn(0); // Stock en cero

        doThrow(new BookNotAvailableException("No hay ejemplares disponibles"))
                .when(validationUtil).checkAvailability(0);

        assertThrows(BookNotAvailableException.class, () -> {
            loanService.createLoan("u1", "b1");
        });

        verify(bookService, never()).updateStock(any(Book.class), anyInt());
    }
}