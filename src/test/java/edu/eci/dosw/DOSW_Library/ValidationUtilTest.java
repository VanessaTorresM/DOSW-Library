package edu.eci.dosw.DOSW_Library;

import edu.eci.dosw.DOSW_Library.Exeption.BookNotAvailableException;
import edu.eci.dosw.DOSW_Library.Util.ValidationUtil;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class ValidationUtilTest {
    private final ValidationUtil validationUtil = new ValidationUtil();

    @Test
    void shouldPassWhenStockIsAvailable() {
        assertDoesNotThrow(() -> validationUtil.checkAvailability(5));
    }

    @Test
    void shouldThrowExceptionWhenStockIsZero() {
        assertThrows(BookNotAvailableException.class, () -> validationUtil.checkAvailability(0));
    }
}
