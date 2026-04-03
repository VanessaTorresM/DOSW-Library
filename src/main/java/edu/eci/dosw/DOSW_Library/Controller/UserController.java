package edu.eci.dosw.DOSW_Library.Controller;

import edu.eci.dosw.DOSW_Library.Modelo.User;
import edu.eci.dosw.DOSW_Library.Service.UserService;
import edu.eci.dosw.DOSW_Library.Validator.UserValidator;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/users")
@Tag(name = "Usuarios", description = "Gestión de socios y perfiles de usuario")
public class UserController {

    private final UserService userService;
    private final UserValidator userValidator;

    public UserController(UserService userService, UserValidator userValidator) {
        this.userService = userService;
        this.userValidator = userValidator;
    }

    @GetMapping
    @Operation(summary = "Listar todos los usuarios")
    public List<User> getAllUsers() {
        return userService.findAll();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obtener detalles de un usuario")
    public User getUserById(@PathVariable String id) {
        return userService.findById(id);
    }

    @PostMapping
    @Operation(summary = "Registrar nuevo usuario", description = "Valida ID y nombre obligatorio")
    public User registerUser(@RequestBody User user) {
        userValidator.validate(user);
        return userService.save(user);
    }
}
