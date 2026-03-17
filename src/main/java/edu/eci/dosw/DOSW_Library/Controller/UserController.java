package edu.eci.dosw.DOSW_Library.Controller;

import edu.eci.dosw.DOSW_Library.Modelo.User;
import edu.eci.dosw.DOSW_Library.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping
    public List<User> getAllUsers() {
        return userService.findAll();
    }

    @GetMapping("/{id}")
    public User getUserById(@PathVariable String id) {
        return userService.findById(id);
    }

    @PostMapping
    public User registerUser(@RequestBody User user) {
        return userService.save(user);
    }
}
