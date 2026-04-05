package edu.eci.dosw.DOSW_Library;


import edu.eci.dosw.DOSW_Library.Modelo.Loan;
import org.junit.jupiter.api.Test;
import java.util.Date;
import static org.junit.jupiter.api.Assertions.*;

class LoanTest {
    @Test
    void testLoanFull() {
        Loan loan1 = new Loan();
        Date fecha = new Date();

        loan1.setLoandate(fecha);
        loan1.setStatus("Active");

        assertEquals(fecha, loan1.getLoandate());
        assertEquals("Active", loan1.getStatus());

        // Test para Equals y HashCode
        Loan loan2 = new Loan();
        loan2.setLoandate(fecha);
        loan2.setStatus("Active");

        assertEquals(loan1, loan2);
        assertEquals(loan1.hashCode(), loan2.hashCode());
        assertNotNull(loan1.toString());
    }
}