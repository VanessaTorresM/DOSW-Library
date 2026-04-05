package edu.eci.dosw.DOSW_Library;

import edu.eci.dosw.DOSW_Library.Controller.BookController;
import edu.eci.dosw.DOSW_Library.Controller.LoanController;
import edu.eci.dosw.DOSW_Library.Modelo.Loan;
import edu.eci.dosw.DOSW_Library.Persistence.Entidades.LoanEntity;
import edu.eci.dosw.DOSW_Library.Persistence.Mapper.BookMapper;
import edu.eci.dosw.DOSW_Library.Persistence.Mapper.LoanMapper;
import edu.eci.dosw.DOSW_Library.Persistence.Mapper.UserMapper;
import edu.eci.dosw.DOSW_Library.Service.LoanService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Collections;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = LoanController.class)
@ContextConfiguration(classes = LoanController.class)
class LoanControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private LoanService loanService;

    @MockitoBean
    private LoanMapper loanMapper;

    @MockitoBean
    private BookMapper bookMapper;

    @MockitoBean
    private UserMapper userMapper;

    @Test
    void shouldCreateLoan() throws Exception {
        LoanEntity mockEntity = new LoanEntity();
        when(loanService.createLoan("u1", "b1")).thenReturn(mockEntity);

        when(loanMapper.toModel(any(LoanEntity.class))).thenReturn(new Loan());

        mockMvc.perform(post("/api/loans")
                        .param("userId", "u1")
                        .param("bookId", "b1"))
                .andExpect(status().isOk());
    }

    @Test
    void shouldGetAllLoans() throws Exception {
        when(loanService.getAllLoans()).thenReturn(Collections.singletonList(new LoanEntity()));

        when(loanMapper.toModel(any(LoanEntity.class))).thenReturn(new Loan());

        mockMvc.perform(get("/api/loans"))
                .andExpect(status().isOk());
    }
}
