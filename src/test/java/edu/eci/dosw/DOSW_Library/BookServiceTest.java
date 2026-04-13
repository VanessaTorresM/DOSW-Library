package edu.eci.dosw.DOSW_Library;

import edu.eci.dosw.DOSW_Library.Modelo.Book;
import edu.eci.dosw.DOSW_Library.Persistence.nonrelational.Document.BookMongoEntity;
import edu.eci.dosw.DOSW_Library.Persistence.nonrelational.Mapper.BookMongoMapper;
import edu.eci.dosw.DOSW_Library.Persistence.nonrelational.Repository.BookRepositoryMongo;
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
    private BookRepositoryMongo bookRepository;

    @Mock
    private BookMongoMapper bookMapper;

    @InjectMocks
    private BookService bookService;

    private Book bookModel;
    private BookMongoEntity bookEntity;

    @BeforeEach
    void setUp() {
        bookModel = new Book("b1", "Martin Fowler", "Refactoring");

        bookEntity = new BookMongoEntity();
        bookEntity.setId("b1");
        bookEntity.setTitle("Refactoring");
        bookEntity.setAuthor("Martin Fowler");
        bookEntity.setAvailableCopies(10);
    }

    @Test
    void shouldSaveBookCorrectly() {
        when(bookMapper.toEntity(any(Book.class))).thenReturn(bookEntity);
        when(bookRepository.save(any(BookMongoEntity.class))).thenReturn(bookEntity);
        when(bookMapper.toModel(any(BookMongoEntity.class))).thenReturn(bookModel);

        Book saved = bookService.save(bookModel);

        assertNotNull(saved);
        verify(bookRepository).save(argThat(entity ->
                entity.getAvailableCopies() == 10 && entity.getId().equals("b1")
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
    void shouldFindEntityDirectly() {
        when(bookRepository.findById("b1")).thenReturn(Optional.of(bookEntity));

        BookMongoEntity entityFound = bookService.findEntityById("b1");

        assertNotNull(entityFound);
        assertEquals("b1", entityFound.getId());
    }
}