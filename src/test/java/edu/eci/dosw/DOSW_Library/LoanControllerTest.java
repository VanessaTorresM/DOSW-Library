package edu.eci.dosw.DOSW_Library;

import com.fasterxml.jackson.databind.ObjectMapper;
import edu.eci.dosw.DOSW_Library.Persistence.nonrelational.Document.BookMongoEntity;
import edu.eci.dosw.DOSW_Library.Persistence.nonrelational.Document.LoanMongoEntity;
import edu.eci.dosw.DOSW_Library.Persistence.nonrelational.Document.UserMongoEntity;
import edu.eci.dosw.DOSW_Library.Persistence.nonrelational.Repository.BookRepositoryMongo;
import edu.eci.dosw.DOSW_Library.Persistence.nonrelational.Repository.UserRepositoryMongo;
import edu.eci.dosw.DOSW_Library.Persistence.nonrelational.Repository.LoanRepositoryMongo;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Collections;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest(properties = {
        "spring.autoconfigure.exclude=org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration,org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration,org.springframework.boot.autoconfigure.mongo.MongoAutoConfiguration"
})
@AutoConfigureMockMvc(addFilters = false)
@ActiveProfiles("test")
@WithMockUser(username = "admin", roles = {"ADMIN"})
class LoanControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private LoanRepositoryMongo loanRepository;

    @MockitoBean
    private BookRepositoryMongo bookRepository;

    @MockitoBean
    private UserRepositoryMongo userRepository;

    @Test
    void shouldGetAllLoans() throws Exception {
        Mockito.when(loanRepository.findAll()).thenReturn(Collections.emptyList());

        mockMvc.perform(get("/api/loans"))
                .andExpect(status().isOk());
    }

    @Test
    void shouldCreateLoan() throws Exception {
        // 1. Creamos la entidad del libro con stock disponible
        BookMongoEntity bookEntity = new BookMongoEntity();
        bookEntity.setId("b1");
        bookEntity.setTitle("Libro de Prueba");
        bookEntity.setAvailableCopies(10); // <--- ESTO ES CLAVE para evitar el 409

        // 2. Creamos la entidad del usuario
        UserMongoEntity userEntity = new UserMongoEntity();
        userEntity.setUserId("u1");
        userEntity.setName("Vanessa");

        Mockito.when(bookRepository.findById("b1")).thenReturn(Optional.of(bookEntity));
        Mockito.when(userRepository.findById("u1")).thenReturn(Optional.of(userEntity));

        LoanMongoEntity savedLoan = new LoanMongoEntity();
        savedLoan.setId("loan-123");
        Mockito.when(loanRepository.save(any())).thenReturn(savedLoan);

        mockMvc.perform(post("/api/loans")
                        .param("userId", "u1")
                        .param("bookId", "b1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }
}