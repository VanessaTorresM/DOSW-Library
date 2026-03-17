package edu.eci.dosw.DOSW_Library.Service;

import edu.eci.dosw.DOSW_Library.Modelo.User;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {
    private final List<User> users = new ArrayList<>();

    public List<User> findAll() {
        return users;
    }

    public User findById(String id) {
        return users.stream()
                .filter(u -> u.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

    public User save(User user) {
        users.add(user);
        return user;
    }
}
