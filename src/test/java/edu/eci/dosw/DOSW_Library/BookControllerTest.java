
package edu.eci.dosw.DOSW_Library;

import com.fasterxml.jackson.databind.ObjectMapper;
import edu.eci.dosw.DOSW_Library.Modelo.Book;
import edu.eci.dosw.DOSW_Library.Persistence.nonrelational.Document.BookMongoEntity;
import edu.eci.dosw.DOSW_Library.Persistence.nonrelational.Repository.BookRepositoryMongo;
import edu.eci.dosw.DOSW_Library.Persistence.nonrelational.Repository.UserRepositoryMongo;
import edu.eci.dosw.DOSW_Library.Persistence.nonrelational.Repository.LoanRepositoryMongo; // <--- IMPORTANTE
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.springframework.security.test.context.support.WithMockUser; // <--- NUEVO IMPORT

@SpringBootTest(properties = {
        "spring.autoconfigure.exclude=org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration,org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration,org.springframework.boot.autoconfigure.mongo.MongoAutoConfiguration"
})
@AutoConfigureMockMvc(addFilters = false)
@ActiveProfiles("test")
@WithMockUser(username = "admin", roles = {"ADMIN"})
class BookControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    // --- MOCKER DE TODOS LOS REPOSITORIOS PARA EVITAR ERRORES DE DEPENDENCIA ---
    @MockitoBean
    private BookRepositoryMongo bookRepository;

    @MockitoBean
    private UserRepositoryMongo userRepository;

    @MockitoBean
    private LoanRepositoryMongo loanRepository; // <--- ESTO EVITA EL ERROR DE 'mongoTemplate'
    // --------------------------------------------------------------------------

    @Test
    void shouldSaveBook() throws Exception {
        Book book = new Book();
        book.setId("b-vanessa-1");
        book.setTitle("Prueba Real");
        book.setAuthor("Vanessa");

        Mockito.when(bookRepository.save(any())).thenReturn(new BookMongoEntity());

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
        BookMongoEntity entity = new BookMongoEntity();
        entity.setId("b20");
        entity.setTitle("Libro Existente");

        Mockito.when(bookRepository.findById("b20")).thenReturn(Optional.of(entity));

        mockMvc.perform(get("/api/books/b20"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.title").value("Libro Existente"));
    }
}