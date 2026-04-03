package edu.eci.dosw.DOSW_Library;


import edu.eci.dosw.DOSW_Library.Exception.ErrorResponse;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class ErrorResponseTest {

    @Test
    void testErrorResponseMethods() {
        long timestamp = System.currentTimeMillis();
        ErrorResponse error1 = new ErrorResponse("Error mensaje", 400, timestamp);

        assertEquals("Error mensaje", error1.getMessage());
        assertEquals(400, error1.getStatus());
        assertEquals(timestamp, error1.getTimestamp());

        error1.setMessage("Nuevo mensaje");
        error1.setStatus(500);
        error1.setTimestamp(12345L);

        assertEquals("Nuevo mensaje", error1.getMessage());
        assertEquals(500, error1.getStatus());
        assertEquals(12345L, error1.getTimestamp());
    }

    @Test
    void testEqualsAndHashCode() {
        long time = System.currentTimeMillis();
        ErrorResponse error1 = new ErrorResponse("Mismo", 200, time);
        ErrorResponse error2 = new ErrorResponse("Mismo", 200, time);
        ErrorResponse error3 = new ErrorResponse("Distinto", 404, time);

        assertEquals(error1, error2);
        assertNotEquals(error1, error3);
        assertEquals(error1.hashCode(), error2.hashCode());
        assertNotEquals(error1.hashCode(), error3.hashCode());

        assertNotEquals(null, error1);
        assertNotEquals("un string", error1);
    }

    @Test
    void testToString() {
        ErrorResponse error = new ErrorResponse("Test", 200, 1000L);
        String toString = error.toString();

        assertTrue(toString.contains("Test"));
        assertTrue(toString.contains("200"));
        assertTrue(toString.contains("1000"));
    }
}
