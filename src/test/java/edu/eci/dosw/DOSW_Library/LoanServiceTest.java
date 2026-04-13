package edu.eci.dosw.DOSW_Library;



import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import edu.eci.dosw.DOSW_Library.Persistence.nonrelational.Document.LoanMongoEntity;
import edu.eci.dosw.DOSW_Library.Persistence.nonrelational.Repository.LoanRepositoryMongo;
import edu.eci.dosw.DOSW_Library.Persistence.nonrelational.Mapper.LoanMongoMapper;
import edu.eci.dosw.DOSW_Library.Service.LoanService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;
import java.util.Collections;
import java.util.List;

@ExtendWith(MockitoExtension.class)
class LoanServiceTest {

    @Mock
    private LoanRepositoryMongo loanRepository;

    @Mock
    private LoanMongoMapper loanMapper;

    @InjectMocks
    private LoanService loanService;

    @Test
    void givenOneLoan_whenFindById_thenSuccess() {
        LoanMongoEntity loan = new LoanMongoEntity();
        loan.setId("loan-123");
        when(loanRepository.findById("loan-123")).thenReturn(Optional.of(loan));

        LoanMongoEntity result = loanService.findById("loan-123");

        assertNotNull(result);
        assertEquals("loan-123", result.getId());
    }

    @Test
    void givenNoLoans_whenFindAll_thenEmpty() {
        when(loanRepository.findAll()).thenReturn(Collections.emptyList());

        List<LoanMongoEntity> result = loanService.getAllLoans();

        assertTrue(result.isEmpty());
    }

    @Test
    void givenNoLoans_whenCreate_thenSuccess() {
        LoanMongoEntity loan = new LoanMongoEntity();
        when(loanRepository.save(any())).thenReturn(loan);

        LoanMongoEntity result = loanRepository.save(new LoanMongoEntity());

        assertNotNull(result);
        verify(loanRepository, times(1)).save(any());
    }

    @Test
    void givenOneLoan_whenDelete_thenSuccess() {
        String id = "loan-123";
        doNothing().when(loanRepository).deleteById(id);

        loanService.deleteById(id);

        verify(loanRepository, times(1)).deleteById(id);
    }

    @Test
    void givenOneLoan_whenDeleteAndFind_thenReturnsNull() {
        String id = "loan-123";
        // Simulamos que el objeto ya no existe en el repo
        when(loanRepository.findById(id)).thenReturn(Optional.empty());

        loanService.deleteById(id);
        LoanMongoEntity result = loanService.findById(id);

        assertNull(result);
    }
}