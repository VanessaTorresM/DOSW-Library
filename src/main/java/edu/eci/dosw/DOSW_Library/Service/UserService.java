package edu.eci.dosw.DOSW_Library.Service;

import edu.eci.dosw.DOSW_Library.Modelo.User;
import edu.eci.dosw.DOSW_Library.Persistence.Entidades.UserEntity;
import edu.eci.dosw.DOSW_Library.Persistence.Mapper.UserMapper;
import edu.eci.dosw.DOSW_Library.Persistence.Repositorios.UserRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.security.crypto.password.PasswordEncoder;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;



    public List<User> findAll() {
        return userRepository.findAll().stream()
                .map(userMapper::toModel)
                .collect(Collectors.toList());
    }

    public User findById(String id) {
        return userRepository.findById(id)
                .map(userMapper::toModel)
                .orElse(null);
    }

    public User save(User user) {
        UserEntity entity = userMapper.toEntity(user);

        entity.setPassword(passwordEncoder.encode(entity.getPassword()));

        UserEntity savedEntity = userRepository.save(entity);

        return userMapper.toModel(savedEntity);
    }

    public UserEntity findEntityById(String id) {
        return userRepository.findById(id).orElse(null);
    }
    public void deleteById(String id) {
        if (!userRepository.existsById(id)) {
            throw new RuntimeException("Usuario no encontrado con ID: " + id);
        }
        userRepository.deleteById(id);
    }
}