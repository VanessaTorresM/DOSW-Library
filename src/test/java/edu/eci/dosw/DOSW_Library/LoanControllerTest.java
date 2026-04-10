package edu.eci.dosw.DOSW_Library;


import edu.eci.dosw.DOSW_Library.Persistence.relational.Entidades.BookEntity;
import edu.eci.dosw.DOSW_Library.Persistence.relational.Entidades.UserEntity;
import edu.eci.dosw.DOSW_Library.Persistence.relational.Repositorios.BookRepository;
import edu.eci.dosw.DOSW_Library.Persistence.relational.Repositorios.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;
import org.springframework.test.context.ActiveProfiles;

import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
@Transactional
class LoanControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BookRepository bookRepository;

    @Test
    @Commit
    void shouldCreateLoan() throws Exception {
        UserEntity user = new UserEntity();
        user.setUserId("u1");
        user.setName("juan");
        user.setUsername("Salazar");
        user.setPassword("123");
        user.setRole("USER");
        userRepository.save(user);

        BookEntity book = new BookEntity();
        book.setId("b1");
        book.setTitle("Software Engineering");
        book.setAuthor("ECI");
        book.setAvailableStock(10);
        book.setTotalStock(10);
        bookRepository.save(book);

        mockMvc.perform(post("/api/loans")
                        .param("userId", "u1")
                        .param("bookId", "b1"))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    void shouldGetAllLoans() throws Exception {
        mockMvc.perform(get("/api/loans"))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json"));
    }
}
