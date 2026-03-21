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

        bookService.save(book, 1);
        Book found = bookService.findById("1");

        assertNotNull(found);
        assertEquals("Clean Code", found.getTitle());
        assertEquals(1, bookService.getStock(found));
    }

    @Test
    void shouldUpdateStockCorrectly() {
        Book book = new Book();
        book.setId("1");
        book.setTitle("Test Book");

        bookService.save(book, 5);
        assertEquals(5, bookService.getStock(book));

        bookService.updateStock(book, 0);
        assertEquals(0, bookService.getStock(book));

        bookService.updateStock(book, 10);
        assertEquals(10, bookService.getStock(book));
    }
}
