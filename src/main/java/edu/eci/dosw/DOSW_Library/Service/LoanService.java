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
    private final LoanMapper loanMapper; // 1. Inyectamos el Mapper

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

        // 2. Preparamos la entidad de préstamo
        LoanEntity newLoanEntity = new LoanEntity();
        newLoanEntity.setUser(userEntity);
        newLoanEntity.setBook(bookEntity);
        newLoanEntity.setLoanDate(new Date());
        newLoanEntity.setStatus("active");

        Loan loanModelParaValidar = loanMapper.toModel(newLoanEntity);

        loanValidator.validateLoan(loanModelParaValidar);
        validationUtil.checkAvailability(bookEntity.getAvailableStock());

        bookEntity.setAvailableStock(bookEntity.getAvailableStock() - 1);
        bookService.saveEntity(bookEntity); // Usamos el método que guarda la entidad

        // 6. Guardamos el préstamo
        return loanRepository.save(newLoanEntity);
    }

    public List<LoanEntity> getAllLoans() {
        return loanRepository.findAll();
    }

    public List<LoanEntity> getLoansByUser(String userId) {
        return loanRepository.findByUser_UserId(userId);
    }
}