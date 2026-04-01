package edu.eci.dosw.DOSW_Library.Validator;


import edu.eci.dosw.DOSW_Library.Modelo.User;
import edu.eci.dosw.DOSW_Library.Exception.LibraryException;
import org.springframework.stereotype.Component;

@Component
public class UserValidator {

    public void validate(User user) {
        if (user.getId() == null || user.getId().isBlank()) {
            throw new LibraryException("El ID del usuario es obligatorio.");
        }
        if (user.getName() == null || user.getName().isBlank()) {
            throw new LibraryException("El nombre del usuario es obligatorio.");
        }
    }
}
