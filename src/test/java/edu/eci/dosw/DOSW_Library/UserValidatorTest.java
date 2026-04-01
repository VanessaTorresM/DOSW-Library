package edu.eci.dosw.DOSW_Library;

import edu.eci.dosw.DOSW_Library.Modelo.User;
import edu.eci.dosw.DOSW_Library.Exception.LibraryException;
import edu.eci.dosw.DOSW_Library.Validator.UserValidator;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class UserValidatorTest {

    private final UserValidator validator = new UserValidator();

    @Test
    void shouldPassWhenUserIsValid() {
        User user = new User();
        user.setId("u1");
        user.setName("Vanessa");

        assertDoesNotThrow(() -> validator.validate(user));
    }

    @Test
    void shouldThrowExceptionWhenIdIsMissing() {
        User user = new User();
        user.setId(null); // ID faltante
        user.setName("Vanessa");

        LibraryException exception = assertThrows(LibraryException.class,
                () -> validator.validate(user));

        assertEquals("El ID del usuario es obligatorio.", exception.getMessage());
    }

    @Test
    void shouldThrowExceptionWhenNameIsBlank() {
        User user = new User();
        user.setId("u1");
        user.setName("   "); // Nombre en blanco

        LibraryException exception = assertThrows(LibraryException.class,
                () -> validator.validate(user));

        assertEquals("El nombre del usuario es obligatorio.", exception.getMessage());
    }
}
