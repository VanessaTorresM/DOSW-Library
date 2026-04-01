package edu.eci.dosw.DOSW_Library;

import edu.eci.dosw.DOSW_Library.Modelo.Status;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class StatusTest {

    @Test
    void testEnumValues() {
        Status[] statuses = Status.values();
        assertEquals(2, statuses.length);

        assertEquals(Status.active, Status.valueOf("active"));
        assertEquals(Status.returned, Status.valueOf("returned"));
    }

    @Test
    void testEnumEntries() {
        for (Status s : Status.values()) {
            assertNotNull(s.name());
            assertTrue(s.ordinal() >= 0);
        }
    }
}
