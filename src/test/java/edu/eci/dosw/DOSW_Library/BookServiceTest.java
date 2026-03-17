package edu.eci.dosw.DOSW_Library;

import edu.eci.dosw.DOSW_Library.Modelo.Book;
import edu.eci.dosw.DOSW_Library.Service.BookService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;


class BookServiceTest {

    private BookService bookService;

    @BeforeEach
    void setUp() {
        bookService = new BookService();
    }

    @Test
    void shouldSaveAndFindBook() {
        Book book = new Book();
        book.setId("1");
        book.setTitle("Clean Code");

        bookService.save(book);
        Book found = bookService.findById("1");

        assertNotNull(found);
        assertEquals("Clean Code", found.getTitle());
    }

    @Test
    void shouldUpdateStockCorrectly() {
        Book book = new Book();
        book.setId("1");
        bookService.save(book);

        // Simular que el libro ya no está disponible
        bookService.updateStock("1", false);
        assertEquals(0, bookService.getStock(book));

        // Simular que vuelve a estar disponible
        bookService.updateStock("1", true);
        assertEquals(1, bookService.getStock(book));
    }
}
