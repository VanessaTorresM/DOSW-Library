package edu.eci.dosw.DOSW_Library.Service;
import edu.eci.dosw.DOSW_Library.Modelo.Loan;
import edu.eci.dosw.DOSW_Library.Modelo.LoanStatus;
import edu.eci.dosw.DOSW_Library.Persistence.relational.Entidades.BookEntity;
import edu.eci.dosw.DOSW_Library.Persistence.relational.Entidades.LoanEntity;
import edu.eci.dosw.DOSW_Library.Persistence.relational.Entidades.UserEntity;
import edu.eci.dosw.DOSW_Library.Persistence.relational.Mapper.LoanMapper;
import edu.eci.dosw.DOSW_Library.Persistence.relational.Repositorios.LoanRepository;
import edu.eci.dosw.DOSW_Library.Util.ValidationUtil;
import org.springframework.stereotype.Service;
import edu.eci.dosw.DOSW_Library.Validator.LoanValidator;

import java.util.Date;
import java.util.List;

import edu.eci.dosw.DOSW_Library.Modelo.Loan;
import edu.eci.dosw.DOSW_Library.Persistence.nonrelational.Document.BookMongoEntity;
import edu.eci.dosw.DOSW_Library.Persistence.nonrelational.Document.LoanMongoEntity;
import edu.eci.dosw.DOSW_Library.Persistence.nonrelational.Document.UserMongoEntity;
import edu.eci.dosw.DOSW_Library.Persistence.nonrelational.Mapper.LoanMongoMapper;
import edu.eci.dosw.DOSW_Library.Persistence.nonrelational.Repository.LoanRepositoryMongo;
// import edu.eci.dosw.DOSW_Library.Persistence.relational.Entidades.LoanEntity;
// import edu.eci.dosw.DOSW_Library.Persistence.relational.Mapper.LoanMapper;
// import edu.eci.dosw.DOSW_Library.Persistence.relational.Repositorios.LoanRepository;
import edu.eci.dosw.DOSW_Library.Util.ValidationUtil;
import edu.eci.dosw.DOSW_Library.Validator.LoanValidator;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class LoanService {

    // Persistencia Relacional (Documentación)
    // private final LoanRepository loanRepository;
    // private final LoanMapper loanMapper;

    // Persistencia No Relacional (MongoDB)
    private final LoanRepositoryMongo loanRepository;
    private final LoanMongoMapper loanMapper;

    private final BookService bookService;
    private final UserService userService;
    private final ValidationUtil validationUtil;
    private final LoanValidator loanValidator;

    public LoanService(LoanRepositoryMongo loanRepository, LoanMongoMapper loanMapper,
                       BookService bookService, UserService userService,
                       ValidationUtil validationUtil, LoanValidator loanValidator) {
        this.loanRepository = loanRepository;
        this.loanMapper = loanMapper;
        this.bookService = bookService;
        this.userService = userService;
        this.validationUtil = validationUtil;
        this.loanValidator = loanValidator;
    }

    public LoanMongoEntity createLoan(String userId, String bookId) {
        // 1. Buscamos las entidades en los servicios de Mongo
        UserMongoEntity userEntity = userService.findEntityById(userId);
        BookMongoEntity bookEntity = bookService.findEntityById(bookId);

        // 2. Creamos la nueva instancia del préstamo
        LoanMongoEntity newLoanEntity = new LoanMongoEntity();
        newLoanEntity.setUser(userEntity);
        newLoanEntity.setBook(bookEntity);
        newLoanEntity.setLoanDate(new Date());

        newLoanEntity.setStatus(LoanStatus.APPROVED);

        Loan loanModelParaValidar = loanMapper.toModel(newLoanEntity);
        loanValidator.validateLoan(loanModelParaValidar);

        validationUtil.checkAvailability(bookEntity.getAvailableCopies());

        bookEntity.setAvailableCopies(bookEntity.getAvailableCopies() - 1);

        bookService.saveEntity(bookEntity);
        return loanRepository.save(newLoanEntity);
    }

    public List<LoanMongoEntity> getAllLoans() {
        return loanRepository.findAll();
    }

    public List<LoanMongoEntity> getLoansByUserId(String userId) {
        return loanRepository.findByUser_UserId(userId);
    }

    public LoanMongoEntity returnLoan(String loanId) { // Cambiado de Long a String para ID de Mongo
        LoanMongoEntity loan = loanRepository.findById(loanId)
                .orElseThrow(() -> new RuntimeException("Préstamo no encontrado"));

        if ("RETURNED".equals(loan.getStatus())) {
            throw new RuntimeException("El libro ya ha sido devuelto");
        }

        BookMongoEntity book = loan.getBook();
        book.setAvailableCopies(book.getAvailableCopies() + 1);
        bookService.saveEntity(book);

        loan.setStatus(LoanStatus.RETURNED);
        loan.setReturnedDate(new Date());

        return loanRepository.save(loan);
    }
    public void deleteById(String id) {
        loanRepository.deleteById(id);
    }

    public LoanMongoEntity findById(String id) {
        return loanRepository.findById(id)
                .orElse(null); // Retorna null si no existe para el test de "sin resultados"
    }
}