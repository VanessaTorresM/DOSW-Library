package edu.eci.dosw.DOSW_Library.Service;

import edu.eci.dosw.DOSW_Library.Modelo.Book;
import edu.eci.dosw.DOSW_Library.Persistence.Entidades.BookEntity;
import edu.eci.dosw.DOSW_Library.Persistence.Mapper.BookMapper;
import edu.eci.dosw.DOSW_Library.Persistence.Repositorios.BookRepository;
import org.springframework.stereotype.Service;
import java.util.*;
import java.util.stream.Collectors;


@Service
public class BookService {
    private final BookRepository bookRepository;
    private final BookMapper bookMapper;

    public BookService(BookRepository bookRepository, BookMapper bookMapper) {
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
        return bookRepository.findByAvailableStockGreaterThan(amount).stream()
                .map(bookMapper::toModel)
                .collect(Collectors.toList());
    }

    public Book save(Book book, int quantity) {
        BookEntity entity = bookMapper.toEntity(book);
        entity.setAvailableStock(quantity);
        BookEntity savedEntity = bookRepository.save(entity);
        return bookMapper.toModel(savedEntity);
    }

    public void saveEntity(BookEntity bookEntity) {
        bookRepository.save(bookEntity);
    }

    public BookEntity findEntityById(String id) {
        return bookRepository.findById(id).orElse(null);
    }

    public void updateStock(Book book, int newQuantity) {
        BookEntity entity = bookRepository.findById(book.getId()).orElse(null);
        if (entity != null) {
            entity.setAvailableStock(newQuantity);
            bookRepository.save(entity);
        }
    }

    public int getStock(Book book) {
        BookEntity entity = bookRepository.findById(book.getId()).orElse(null);
        return (entity != null) ? entity.getAvailableStock() : 0;
    }
}