package edu.eci.dosw.DOSW_Library;

import edu.eci.dosw.DOSW_Library.Modelo.Book;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class BookTest {

    @Test
    void testBookGettersAndSetters() {
        Book book = new Book();
        book.setId("b1");
        book.setTitle("Clean Code");
        book.setAuthor("Robert C. Martin");

        assertEquals("b1", book.getId());
        assertEquals("Clean Code", book.getTitle());
        assertEquals("Robert C. Martin", book.getAuthor());
        assertNotNull(book.toString());
    }

    @Test
    void testBookEquality() {
        Book book1 = new Book("1", "Robert C. Martin", "Clean Code");
        Book book2 = new Book("1", "Robert C. Martin", "Clean Code");
        Book book3 = new Book("2", "Andy Hunt", "Pragmatic Programmer");

        assertEquals(book1, book2);
        assertNotEquals(book1, book3);
        assertNotEquals(null, book1);
        assertNotEquals("no es un libro", book1);

        // hashCode
        assertEquals(book1.hashCode(), book2.hashCode());
        assertNotEquals(book1.hashCode(), book3.hashCode());
    }

    @Test
    void testBookFullConstructor() {
        Book book = new Book("b1", "Martin Fowler", "Refactoring");

        assertEquals("b1", book.getId());
        assertEquals("Refactoring", book.getTitle());
        assertEquals("Martin Fowler", book.getAuthor());
    }
}