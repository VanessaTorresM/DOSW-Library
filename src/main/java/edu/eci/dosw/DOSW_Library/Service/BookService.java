package edu.eci.dosw.DOSW_Library.Service;

import edu.eci.dosw.DOSW_Library.Modelo.Book;
import edu.eci.dosw.DOSW_Library.Persistence.nonrelational.Document.BookMongoEntity;
import edu.eci.dosw.DOSW_Library.Persistence.nonrelational.Mapper.BookMongoMapper;
import edu.eci.dosw.DOSW_Library.Persistence.nonrelational.Repository.BookRepositoryMongo;
// import edu.eci.dosw.DOSW_Library.Persistence.relational.Entidades.BookEntity;
// import edu.eci.dosw.DOSW_Library.Persistence.relational.Mapper.BookMapper;
// import edu.eci.dosw.DOSW_Library.Persistence.relational.Repositorios.BookRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BookService {

    // Persistencia Relacional (Documentación)
    // private final BookRepository bookRepository;
    // private final BookMapper bookMapper;

    // Persistencia No Relacional (MongoDB)
    private final BookRepositoryMongo bookRepository;
    private final BookMongoMapper bookMapper;

    public BookService(BookRepositoryMongo bookRepository, BookMongoMapper bookMapper) {
        this.bookRepository = bookRepository;
        this.bookMapper = bookMapper;
    }

    public List<Book> findAll() {
        return bookRepository.findAll().stream()
                .map(bookMapper::toModel)
                .collect(Collectors.toList());
    }

    public Book findById(String id) {
        return bookRepository.findById(id)
                .map(bookMapper::toModel)
                .orElse(null);
    }

    public List<Book> findAvailable(int amount) {
        return bookRepository.findByAvailableCopiesGreaterThan(amount).stream()
                .map(bookMapper::toModel)
                .collect(Collectors.toList());
    }

    public Book save(Book book) {
        BookMongoEntity entity = bookMapper.toEntity(book);
        BookMongoEntity savedEntity = bookRepository.save(entity);
        return bookMapper.toModel(savedEntity);
    }

    public void saveEntity(BookMongoEntity bookEntity) {
        bookRepository.save(bookEntity);
    }

    public BookMongoEntity findEntityById(String id) {
        return bookRepository.findById(id).orElse(null);
    }

    public void updateStock(Book book, int newQuantity) {
        BookMongoEntity entity = bookRepository.findById(book.getId()).orElse(null);
        if (entity != null) {
            entity.setAvailableCopies(newQuantity);
            bookRepository.save(entity);
        }
    }

    public int getStock(Book book) {
        BookMongoEntity entity = bookRepository.findById(book.getId()).orElse(null);
        return (entity != null) ? entity.getAvailableCopies() : 0;
    }

    public void deleteById(String id) {
        if (!bookRepository.existsById(id)) {
            throw new RuntimeException("Libro no encontrado con ID: " + id);
        }
        bookRepository.deleteById(id);
    }
}