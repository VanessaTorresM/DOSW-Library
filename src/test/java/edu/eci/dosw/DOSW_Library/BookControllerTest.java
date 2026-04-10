
package edu.eci.dosw.DOSW_Library;

import com.fasterxml.jackson.databind.ObjectMapper;
import edu.eci.dosw.DOSW_Library.Modelo.Book;
import edu.eci.dosw.DOSW_Library.Persistence.relational.Entidades.BookEntity;
import edu.eci.dosw.DOSW_Library.Persistence.relational.Repositorios.BookRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.Commit;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
@Transactional
class BookControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private BookRepository bookRepository;

    @Test
    @Commit
    void shouldSaveBook() throws Exception {
        Book book = new Book();
        book.setId("b-vanessa-1");
        book.setTitle("Prueba Real");
        book.setAuthor("Vanessa");

        mockMvc.perform(post("/api/books")
                        .param("quantity", "10")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(book)))
                .andExpect(status().isOk());
    }

    @Test
    void shouldGetAllBooks() throws Exception {
        mockMvc.perform(get("/api/books"))
                .andExpect(status().isOk());
    }

    @Test
    void shouldGetBookById() throws Exception {
        BookEntity entity = new BookEntity();
        entity.setId("b20");
        entity.setTitle("Libro Existente");
        entity.setAuthor("Autor");
        entity.setAvailableStock(5);
        entity.setTotalStock(5);
        bookRepository.save(entity);

        mockMvc.perform(get("/api/books/b20"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.title").value("Libro Existente"));
    }

    @Test
    @Commit
    void shouldUpdateStock() throws Exception {
        BookEntity entity = new BookEntity();
        entity.setId("b1");
        entity.setTitle("Libro para Update");
        entity.setAuthor("Autor");
        entity.setAvailableStock(10);
        entity.setTotalStock(10);
        bookRepository.save(entity);

        mockMvc.perform(put("/api/books/b1/stock")
                        .param("quantity", "50"))
                .andExpect(status().isOk());

        System.out.println("Nuevo stock: " + bookRepository.findById("b1").get().getAvailableStock());
    }
}