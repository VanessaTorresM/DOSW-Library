package edu.eci.dosw.DOSW_Library.Validator;


import edu.eci.dosw.DOSW_Library.Modelo.Book;
import edu.eci.dosw.DOSW_Library.Exception.LibraryException;
import org.springframework.stereotype.Component;


@Component
public class BookValidator {

    public void validate(Book book) {
        if (book.getTitle() == null || book.getTitle().isBlank()) {
            throw new LibraryException("El título del libro es obligatorio.");
        }
        if (book.getAuthor() == null || book.getAuthor().isBlank()) {
            throw new LibraryException("El autor del libro es obligatorio.");
        }
    }
}
