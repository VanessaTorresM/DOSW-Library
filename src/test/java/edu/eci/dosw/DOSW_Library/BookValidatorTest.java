package edu.eci.dosw.DOSW_Library;


import edu.eci.dosw.DOSW_Library.Modelo.Book;
import edu.eci.dosw.DOSW_Library.Exception.LibraryException;
import edu.eci.dosw.DOSW_Library.Validator.BookValidator;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class BookValidatorTest {

    private final BookValidator validator = new BookValidator();

    @Test
    void shouldPassValidationWhenBookIsCorrect() {
        Book book = new Book("b1", "Martin Fowler", "Refactoring");
        assertDoesNotThrow(() -> validator.validate(book));
    }

    @Test
    void shouldThrowExceptionWhenTitleIsBlank() {
        Book book = new Book("b1", "Martin Fowler", ""); // Título vacío

        LibraryException exception = assertThrows(LibraryException.class,
                () -> validator.validate(book));

        assertEquals("El título del libro es obligatorio.", exception.getMessage());
    }

    @Test
    void shouldThrowExceptionWhenAuthorIsNull() {
        Book book = new Book("b1", null, "Refactoring"); // Autor nulo

        LibraryException exception = assertThrows(LibraryException.class,
                () -> validator.validate(book));

        assertEquals("El autor del libro es obligatorio.", exception.getMessage());
    }
}
