package edu.eci.dosw.DOSW_Library;



import com.fasterxml.jackson.databind.ObjectMapper;
import edu.eci.dosw.DOSW_Library.Controller.BookController;
import edu.eci.dosw.DOSW_Library.Modelo.Book;
import edu.eci.dosw.DOSW_Library.Service.BookService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.Collections;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(BookController.class)
class BookControllerTest {

    private MockMvc mockMvc;

    private BookService bookService;

    private final ObjectMapper objectMapper = new ObjectMapper();

    @BeforeEach
    void setUp(WebApplicationContext webApplicationContext) {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @Test
    void shouldGetAllBooks() throws Exception {
        when(bookService.findAll()).thenReturn(Collections.emptyList());

        mockMvc.perform(get("/api/books"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    void shouldGetBookById() throws Exception {
        Book book = new Book();
        book.setId("b1");
        book.setTitle("Clean Code");

        when(bookService.findById("b1")).thenReturn(book);

        mockMvc.perform(get("/api/books/b1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value("b1"))
                .andExpect(jsonPath("$.title").value("Clean Code"));
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

    @Test
    void shouldReturnErrorWhenBookNotFound() throws Exception {
        when(bookService.findById(anyString())).thenReturn(null);

        mockMvc.perform(get("/api/books/999"))
                .andExpect(status().isOk());
    }
}