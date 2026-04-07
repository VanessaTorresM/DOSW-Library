package edu.eci.dosw.DOSW_Library.Controller;

import edu.eci.dosw.DOSW_Library.Modelo.User;
import edu.eci.dosw.DOSW_Library.Persistence.Mapper.UserMapper;
import edu.eci.dosw.DOSW_Library.Service.UserService;
import edu.eci.dosw.DOSW_Library.Validator.UserValidator;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
@Tag(name = "Usuarios", description = "Gestión de socios y perfiles de usuario")
public class UserController {

    private final UserService userService;
    private final UserValidator userValidator;
    private final UserMapper userMapper;

    public UserController(UserService userService, UserValidator userValidator, UserMapper userMapper) {
        this.userService = userService;
        this.userValidator = userValidator;
        this.userMapper = userMapper;
    }

    @GetMapping
    @PreAuthorize("hasRole('LIBRARIAN')")
    @Operation(summary = "Listar todos los usuarios")
    public List<User> getAllUsers() {
        return userService.findAll();
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('USER', 'LIBRARIAN')")
    @Operation(summary = "Obtener detalles de un usuario")
    public User getUserById(@PathVariable String id) {
        return userService.findById(id);
    }

    @PostMapping
    @PreAuthorize("hasRole('LIBRARIAN')")
    @Operation(summary = "Registrar nuevo usuario")
    public User registerUser(@RequestBody User user) {
        userValidator.validate(user);
        return userService.save(user);
    }
}
