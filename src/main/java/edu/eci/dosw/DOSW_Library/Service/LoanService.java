package edu.eci.dosw.DOSW_Library.Service;
import edu.eci.dosw.DOSW_Library.Modelo.Loan;
import edu.eci.dosw.DOSW_Library.Persistence.Entidades.BookEntity;
import edu.eci.dosw.DOSW_Library.Persistence.Entidades.LoanEntity;
import edu.eci.dosw.DOSW_Library.Persistence.Entidades.UserEntity;
import edu.eci.dosw.DOSW_Library.Persistence.Mapper.LoanMapper;
import edu.eci.dosw.DOSW_Library.Persistence.Repositorios.LoanRepository;
import edu.eci.dosw.DOSW_Library.Util.ValidationUtil;
import org.springframework.stereotype.Service;
import edu.eci.dosw.DOSW_Library.Validator.LoanValidator;

import java.util.Date;
import java.util.List;

@Service
public class LoanService {

    private final LoanRepository loanRepository;
    private final BookService bookService;
    private final UserService userService;
    private final ValidationUtil validationUtil;
    private final LoanValidator loanValidator;
    private final LoanMapper loanMapper;

    public LoanService(LoanRepository loanRepository, BookService bookService,
                       UserService userService, ValidationUtil validationUtil,
                       LoanValidator loanValidator, LoanMapper loanMapper) {
        this.loanRepository = loanRepository;
        this.bookService = bookService;
        this.userService = userService;
        this.validationUtil = validationUtil;
        this.loanValidator = loanValidator;
        this.loanMapper = loanMapper;
    }

    public LoanEntity createLoan(String userId, String bookId) {

        UserEntity userEntity = userService.findEntityById(userId);
        BookEntity bookEntity = bookService.findEntityById(bookId);

        LoanEntity newLoanEntity = new LoanEntity();
        newLoanEntity.setUser(userEntity);
        newLoanEntity.setBook(bookEntity);
        newLoanEntity.setLoanDate(new Date());
        newLoanEntity.setStatus("active");

        Loan loanModelParaValidar = loanMapper.toModel(newLoanEntity);

        loanValidator.validateLoan(loanModelParaValidar);
        validationUtil.checkAvailability(bookEntity.getAvailableStock());

        bookEntity.setAvailableStock(bookEntity.getAvailableStock() - 1);
        bookService.saveEntity(bookEntity);
        return loanRepository.save(newLoanEntity);
    }

    public List<LoanEntity> getAllLoans() {
        return loanRepository.findAll();
    }

    public List<LoanEntity> getLoansByUser(String userId) {
        return loanRepository.findByUser_UserId(userId);
    }

    public List<LoanEntity> getLoansByUserId(String userId) {
        return loanRepository.findByUser_UserId(userId);
    }


    public LoanEntity returnLoan(Long loanId) {
        LoanEntity loan = loanRepository.findById(loanId)
                .orElseThrow(() -> new RuntimeException("Préstamo no encontrado"));

        if ("RETURNED".equals(loan.getStatus())) {
        throw new RuntimeException("El libro ya ha sido devuelto");
        }

        BookEntity book = loan.getBook();
        book.setAvailableStock(book.getAvailableStock() + 1);
        bookService.saveEntity(book);

        loan.setStatus("RETURNED");
        loan.setReturnedDate(new Date());

        return loanRepository.save(loan);
    }
}