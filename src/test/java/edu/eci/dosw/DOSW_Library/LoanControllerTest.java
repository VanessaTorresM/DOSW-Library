package edu.eci.dosw.DOSW_Library;

import edu.eci.dosw.DOSW_Library.Controller.LoanController;
import edu.eci.dosw.DOSW_Library.Modelo.loan;
import edu.eci.dosw.DOSW_Library.Service.LoanService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(LoanController.class)
class LoanControllerTest {

    private MockMvc mockMvc;

    private LoanService loanService;

    @BeforeEach
    void setUp(WebApplicationContext webApplicationContext) {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @Test
    void shouldCreateLoan() throws Exception {
        when(loanService.createLoan("u1", "b1")).thenReturn(new loan());

        mockMvc.perform(post("/api/loans")
                        .param("userId", "u1")
                        .param("bookId", "b1"))
                .andExpect(status().isOk());
    }
}
