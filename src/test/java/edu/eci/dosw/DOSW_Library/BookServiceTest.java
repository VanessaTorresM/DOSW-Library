package edu.eci.dosw.DOSW_Library;

import edu.eci.dosw.DOSW_Library.Modelo.Book;
import edu.eci.dosw.DOSW_Library.Service.BookService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class BookServiceTest {

    private BookService bookService;

    @BeforeEach
    void setUp() {
        bookService = new BookService();
    }

    // --- TESTS DE CAMINO FELIZ (Ya los tenías) ---

    @Test
    void shouldSaveAndFindBook() {
        Book book = new Book();
        book.setId("1");
        book.setTitle("Clean Code");

        bookService.save(book, 1);
        Book found = bookService.findById("1");

        assertNotNull(found);
        assertEquals("Clean Code", found.getTitle());
    }

    @Test
    void shouldUpdateStockCorrectly() {
        Book book = new Book();
        book.setId("1");
        bookService.save(book, 5);

        bookService.updateStock(book, 10);
        assertEquals(10, bookService.getStock(book));
    }

    // --- TESTS PARA SUBIR COBERTURA (Nuevos) ---

    @Test
    void shouldReturnNullWhenBookNotFoundById() {
        // Probamos el .orElse(null) del stream
        Book found = bookService.findById("999"); // ID que no existe
        assertNull(found, "Debe retornar null si el ID no existe");
    }

    @Test
    void shouldReturnZeroStockForNonExistentBook() {
        // Probamos el .getOrDefault(book, 0)
        Book bookNotSaved = new Book();
        bookNotSaved.setId("99");

        int stock = bookService.getStock(bookNotSaved);
        assertEquals(0, stock, "El stock de un libro no registrado debe ser 0");
    }

    @Test
    void shouldFindAllBooks() {
        // Probamos el método findAll() y la conversión a ArrayList
        Book b1 = new Book(); b1.setId("b1");
        Book b2 = new Book(); b2.setId("b2");

        bookService.save(b1, 1);
        bookService.save(b2, 1);

        List<Book> all = bookService.findAll();
        assertEquals(2, all.size());
        assertTrue(all.contains(b1));
        assertTrue(all.contains(b2));
    }

    @Test
    void shouldIncrementStockWhenSavingSameBookMultipleTimes() {
        // Probamos la lógica del getOrDefault dentro del método save
        Book book = new Book();
        book.setId("1");

        bookService.save(book, 2);
        bookService.save(book, 3);

        assertEquals(5, bookService.getStock(book), "El stock debería sumarse (2+3=5)");
    }
}
