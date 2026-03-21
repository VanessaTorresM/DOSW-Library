package edu.eci.dosw.DOSW_Library.Service;

import edu.eci.dosw.DOSW_Library.Modelo.Book;
import org.springframework.stereotype.Service;
import java.util.*;

@Service
public class BookService {
    private final Map<Book, Integer> bookStock = new HashMap<>();

    public List<Book> findAll() {
        return new ArrayList<>(bookStock.keySet());
    }

    public Book findById(String id) {
        return bookStock.keySet().stream()
                .filter(b -> b.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

    public Book save(Book book, int quantity) {
        bookStock.put(book, bookStock.getOrDefault(book, 0) + quantity);
        return book;
    }

    public void updateStock(Book book, int available) {
        bookStock.put(book, available);
    }

    public int getStock(Book book) {
        return bookStock.getOrDefault(book, 0);
    }
}