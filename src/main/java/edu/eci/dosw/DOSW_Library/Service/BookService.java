package edu.eci.dosw.DOSW_Library.Service;

import edu.eci.dosw.DOSW_Library.Modelo.Book;
import org.springframework.stereotype.Service;
import java.util.*;

@Service
public class BookService {
    // Mapa: Clave = Libro, Valor = Cantidad disponible
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

    public Book save(Book book) {
        // Al agregar un libro nuevo, iniciamos con 1 ejemplar o sumamos si ya existe
        bookStock.put(book, bookStock.getOrDefault(book, 0) + 1);
        return book;
    }

    public void updateStock(String id, boolean available) {
        Book book = findById(id);
        if (book != null) {
            // Lógica simple: si no está disponible ponemos stock en 0, si lo está, en 1
            bookStock.put(book, available ? 1 : 0);
        }
    }

    public int getStock(Book book) {
        return bookStock.getOrDefault(book, 0);
    }
}
