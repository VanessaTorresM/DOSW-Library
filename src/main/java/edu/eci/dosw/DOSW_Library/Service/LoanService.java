package edu.eci.dosw.DOSW_Library.Service;

import edu.eci.dosw.DOSW_Library.Modelo.Book;
import edu.eci.dosw.DOSW_Library.Modelo.User;
import edu.eci.dosw.DOSW_Library.Modelo.loan;
import edu.eci.dosw.DOSW_Library.Modelo.Status;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class LoanService {
    private final List<loan> loans = new ArrayList<>();

    @Autowired
    private BookService bookService;
    @Autowired
    private UserService userService;

    public loan borrowBook(String userId, String bookId) {
        User user = userService.findById(userId);
        Book book = bookService.findById(bookId);

        if (user != null && book != null && bookService.getStock(book) > 0) {
            loan newLoan = new loan();
            newLoan.setUser(user);
            newLoan.setBook(book);
            newLoan.setLoandate(new Date());
            newLoan.setStatus(Status.active.name());

            // Reducir el stock del mapa en BookService
            bookService.updateStock(bookId, false);
            loans.add(newLoan);
            return newLoan;
        }
        return null;
    }

    public List<loan> findAll() {
        return loans;
    }

    public loan returnBook(String loanId) {
        // Lógica para marcar como 'returned' y devolver el libro al stock
        return null; // Implementar según necesidad de búsqueda de préstamo
    }
}
