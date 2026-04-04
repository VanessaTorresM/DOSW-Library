package edu.eci.dosw.DOSW_Library.Service;

import edu.eci.dosw.DOSW_Library.Modelo.User;
import edu.eci.dosw.DOSW_Library.Persistence.Entidades.UserEntity;
import edu.eci.dosw.DOSW_Library.Persistence.Mapper.UserMapper;
import edu.eci.dosw.DOSW_Library.Persistence.Repositorios.UserRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;


@Service
public class UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;

    public UserService(UserRepository userRepository, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }

    public List<User> findAll() {
        // Programación funcional: Entidad -> Modelo
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
        UserEntity savedEntity = userRepository.save(entity);
        return userMapper.toModel(savedEntity);
    }

    public UserEntity findEntityById(String id) {
        return userRepository.findById(id).orElse(null);
    }
}