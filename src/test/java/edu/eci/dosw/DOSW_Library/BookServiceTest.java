package edu.eci.dosw.DOSW_Library;

import edu.eci.dosw.DOSW_Library.Modelo.Book;
import edu.eci.dosw.DOSW_Library.Persistence.Entidades.BookEntity;
import edu.eci.dosw.DOSW_Library.Persistence.Mapper.BookMapper;
import edu.eci.dosw.DOSW_Library.Persistence.Repositorios.BookRepository;
import edu.eci.dosw.DOSW_Library.Service.BookService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class BookServiceTest {

    @Mock
    private BookRepository bookRepository;

    @Mock
    private BookMapper bookMapper;

    @InjectMocks
    private BookService bookService;

    private Book bookModel;
    private BookEntity bookEntity;

    @BeforeEach
    void setUp() {
        bookModel = new Book("b1", "Martin Fowler", "Refactoring");

        bookEntity = new BookEntity();
        bookEntity.setBookId("b1");
        bookEntity.setTitle("Refactoring");
        bookEntity.setAuthor("Martin Fowler");
        bookEntity.setTotalStock(10);
        bookEntity.setAvailableStock(10);
    }

    @Test
    void shouldSaveBookCorrectly() {
        when(bookMapper.toEntity(any(Book.class))).thenReturn(bookEntity);
        when(bookRepository.save(any(BookEntity.class))).thenReturn(bookEntity);
        when(bookMapper.toModel(any(BookEntity.class))).thenReturn(bookModel);

        Book saved = bookService.save(bookModel, 10);

        assertNotNull(saved);
        verify(bookRepository).save(argThat(entity ->
                entity.getAvailableStock() == 10 && entity.getBookId().equals("b1")
        ));
    }

    @Test
    void shouldFindByIdUsingEntity() {
        when(bookRepository.findById("b1")).thenReturn(Optional.of(bookEntity));
        when(bookMapper.toModel(bookEntity)).thenReturn(bookModel);

        Book found = bookService.findById("b1");

        assertNotNull(found);
        assertEquals("Refactoring", found.getTitle());
    }

    @Test
    void shouldReturnNullWhenNotFoundInDB() {
        when(bookRepository.findById("non-existent")).thenReturn(Optional.empty());

        Book found = bookService.findById("non-existent");

        assertNull(found);
    }

    @Test
    void shouldFindAvailableBooksByStock() {
        when(bookRepository.findByAvailableStockGreaterThan(5))
                .thenReturn(Collections.singletonList(bookEntity));
        when(bookMapper.toModel(bookEntity)).thenReturn(bookModel);

        List<Book> result = bookService.findAvailable(5);

        assertFalse(result.isEmpty());
        assertEquals(1, result.size());
        verify(bookRepository).findByAvailableStockGreaterThan(5);
    }

    @Test
    void shouldFindEntityDirectly() {
        when(bookRepository.findById("b1")).thenReturn(Optional.of(bookEntity));

        BookEntity entityFound = bookService.findEntityById("b1");

        assertNotNull(entityFound);
        assertEquals("b1", entityFound.getBookId());
        assertEquals(10, entityFound.getAvailableStock());
    }
}