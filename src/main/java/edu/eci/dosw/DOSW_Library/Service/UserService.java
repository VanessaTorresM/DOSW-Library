package edu.eci.dosw.DOSW_Library.Service;

import edu.eci.dosw.DOSW_Library.Modelo.User;
import edu.eci.dosw.DOSW_Library.Persistence.relational.Entidades.UserEntity;
import edu.eci.dosw.DOSW_Library.Persistence.relational.Mapper.UserMapper;
import edu.eci.dosw.DOSW_Library.Persistence.relational.Repositorios.UserRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.security.crypto.password.PasswordEncoder;
import lombok.RequiredArgsConstructor;

import edu.eci.dosw.DOSW_Library.Modelo.User;
import edu.eci.dosw.DOSW_Library.Persistence.nonrelational.Document.UserMongoEntity;
import edu.eci.dosw.DOSW_Library.Persistence.nonrelational.Mapper.UserMongoMapper;
import edu.eci.dosw.DOSW_Library.Persistence.nonrelational.Repository.UserRepositoryMongo;
// import edu.eci.dosw.DOSW_Library.Persistence.relational.Entidades.UserEntity;
// import edu.eci.dosw.DOSW_Library.Persistence.relational.Mapper.UserMapper;
// import edu.eci.dosw.DOSW_Library.Persistence.relational.Repositorios.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class UserService {

    // Persistencia Relacional (Documentación)
    // private final UserRepository userRepository;
    // private final UserMapper userMapper;

    // Persistencia No Relacional (MongoDB)
    private final UserRepositoryMongo userRepository; //
    private final UserMongoMapper userMapper; //
    private final PasswordEncoder passwordEncoder;

    public List<User> findAll() {
        return userRepository.findAll().stream()
                .map(userMapper::toModel) //
                .collect(Collectors.toList());
    }

    public User findById(String id) {
        return userRepository.findById(id)
                .map(userMapper::toModel)
                .orElse(null);
    }

    public User save(User user) {
        UserMongoEntity entity = userMapper.toEntity(user); //

        // Encriptar contraseña antes de guardar en Atlas
        entity.setPassword(passwordEncoder.encode(entity.getPassword()));

        UserMongoEntity savedEntity = userRepository.save(entity);

        return userMapper.toModel(savedEntity);
    }

    public UserMongoEntity findEntityById(String id) {
        return userRepository.findById(id).orElse(null);
    }

    public void deleteById(String id) {
        if (!userRepository.existsById(id)) {
            throw new RuntimeException("Usuario no encontrado con ID: " + id);
        }
        userRepository.deleteById(id);
    }
}