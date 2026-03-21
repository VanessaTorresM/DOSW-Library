package edu.eci.dosw.DOSW_Library.Service;
import edu.eci.dosw.DOSW_Library.Modelo.Book;
import edu.eci.dosw.DOSW_Library.Modelo.User;
import edu.eci.dosw.DOSW_Library.Modelo.loan;
import edu.eci.dosw.DOSW_Library.Util.ValidationUtil;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class LoanService {

    private final List<loan> loans = new ArrayList<>();
    private final BookService bookService;
    private final UserService userService;
    private final ValidationUtil validationUtil;

    // Inyección por constructor: Sin @Autowired
    public LoanService(BookService bookService, UserService userService, ValidationUtil validationUtil) {
        this.bookService = bookService;
        this.userService = userService;
        this.validationUtil = validationUtil;
    }

    public loan createLoan(String userId, String bookId) {
        User user = userService.findById(userId);
        Book book = bookService.findById(bookId);

        if (user == null || book == null) {
            throw new RuntimeException("Usuario o Libro no encontrado");
        }

        // 1. Validamos disponibilidad
        validationUtil.checkAvailability(bookService.getStock(book));

        // 2. Actualizamos el stock (Bajamos 1 unidad en el mapa)
        bookService.updateStock(book, bookService.getStock(book) - 1);

        // 3. Creamos el objeto préstamo
        loan newLoan = new loan();
        newLoan.setUser(user);
        newLoan.setBook(book);
        newLoan.setLoanDate(new Date()); // Asegúrate que en loan.java sea 'loandate'
        newLoan.setStatus("active");

        loans.add(newLoan);
        return newLoan;
    }

    public List<loan> getAllLoans() {
        return loans;
    }
}
