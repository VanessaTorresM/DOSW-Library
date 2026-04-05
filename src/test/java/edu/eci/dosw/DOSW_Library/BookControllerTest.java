package edu.eci.dosw.DOSW_Library;



import com.fasterxml.jackson.databind.ObjectMapper;
import edu.eci.dosw.DOSW_Library.Controller.BookController;
import edu.eci.dosw.DOSW_Library.Modelo.Book;
import edu.eci.dosw.DOSW_Library.Persistence.Mapper.BookMapper;
import edu.eci.dosw.DOSW_Library.Service.BookService;
import edu.eci.dosw.DOSW_Library.Validator.BookValidator;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Collections;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.springframework.test.context.bean.override.mockito.MockitoBean; // Nuevo Import

@WebMvcTest(BookController.class)
class BookControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockitoBean
    private BookService bookService;

    @MockitoBean
    private BookValidator bookValidator;

    @MockitoBean
    private BookMapper bookMapper;

    @Test
    void shouldGetAllBooks() throws Exception {
        when(bookService.findAll()).thenReturn(Collections.emptyList());

        mockMvc.perform(get("/api/books"))
                .andExpect(status().isOk());
    }

    @Test
    void shouldGetBookById() throws Exception {
        Book book = new Book();
        book.setId("b1");
        book.setTitle("Clean Code");

        when(bookService.findById("b1")).thenReturn(book);

        mockMvc.perform(get("/api/books/b1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value("b1"));
    }

    @Test
    void shouldSaveBook() throws Exception {
        Book book = new Book();
        book.setId("b2");
        book.setTitle("Refactoring");

        when(bookService.save(any(Book.class), anyInt())).thenReturn(book);

        mockMvc.perform(post("/api/books")
                        .param("quantity", "10")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(book)))
                .andExpect(status().isOk());

        verify(bookValidator).validate(any(Book.class));
    }

    @Test
    void shouldUpdateStock() throws Exception {
        Book book = new Book();
        book.setId("b1");

        when(bookService.findById("b1")).thenReturn(book);

        mockMvc.perform(put("/api/books/b1/stock")
                        .param("quantity", "20"))
                .andExpect(status().isOk());
    }
}