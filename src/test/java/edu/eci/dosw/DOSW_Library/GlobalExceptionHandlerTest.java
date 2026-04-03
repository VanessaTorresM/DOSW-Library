package edu.eci.dosw.DOSW_Library;


import edu.eci.dosw.DOSW_Library.Exception.BookNotAvailableException;
import edu.eci.dosw.DOSW_Library.Exception.ErrorResponse;
import edu.eci.dosw.DOSW_Library.Exception.GlobalExceptionHandler;
import edu.eci.dosw.DOSW_Library.Exception.LibraryException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.*;

class GlobalExceptionHandlerTest {

    private GlobalExceptionHandler handler;

    @BeforeEach
    void setUp() {
        handler = new GlobalExceptionHandler();
    }

    @Test
    void testHandleLibraryException() {
        String message = "Error de librería";
        LibraryException ex = new LibraryException(message);

        ResponseEntity<ErrorResponse> response = handler.handleLibraryException(ex);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(message, response.getBody().getMessage());
        assertEquals(HttpStatus.BAD_REQUEST.value(), response.getBody().getStatus());
    }

    @Test
    void testHandleBookNotAvailableException() {
        String message = "Libro no disponible";
        BookNotAvailableException ex = new BookNotAvailableException(message);

        ResponseEntity<ErrorResponse> response = handler.handleBookNotAvailable(ex);

        assertEquals(HttpStatus.CONFLICT, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(message, response.getBody().getMessage());
        assertEquals(HttpStatus.CONFLICT.value(), response.getBody().getStatus());
    }

    @Test
    void testHandleGeneralException() {
        String message = "Falla crítica";
        Exception ex = new Exception(message);

        ResponseEntity<ErrorResponse> response = handler.handleGeneralException(ex);

        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
        assertNotNull(response.getBody());
        assertTrue(response.getBody().getMessage().contains(message));
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR.value(), response.getBody().getStatus());
    }
}
