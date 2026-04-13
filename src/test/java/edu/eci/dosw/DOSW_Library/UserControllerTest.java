package edu.eci.dosw.DOSW_Library;

import com.fasterxml.jackson.databind.ObjectMapper;
import edu.eci.dosw.DOSW_Library.Modelo.User;
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
class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockitoBean
    private UserRepositoryMongo userRepository;

    @MockitoBean
    private BookRepositoryMongo bookRepository;

    @MockitoBean
    private LoanRepositoryMongo loanRepository;

    @Test
    void shouldCreateUser() throws Exception {
        User user = new User("u1", "Vanessa Torres", "vane123", "pass123", "ADMIN");

        Mockito.when(userRepository.save(any())).thenReturn(new UserMongoEntity());

        mockMvc.perform(post("/api/users/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(user)))
                .andExpect(status().isOk());
    }

    @Test
    void shouldGetUserById() throws Exception {
        UserMongoEntity entity = new UserMongoEntity();
        entity.setUserId("u1");
        entity.setName("Vanessa");

        Mockito.when(userRepository.findById("u1")).thenReturn(Optional.of(entity));

        mockMvc.perform(get("/api/users/u1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Vanessa"));
    }
}