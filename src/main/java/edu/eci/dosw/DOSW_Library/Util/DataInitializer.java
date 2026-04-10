package edu.eci.dosw.DOSW_Library.Util;

import edu.eci.dosw.DOSW_Library.Persistence.nonrelational.Document.UserMongoEntity;
import edu.eci.dosw.DOSW_Library.Persistence.nonrelational.Repository.UserRepositoryMongo;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDateTime;

@Configuration
public class DataInitializer {

    @Bean
    CommandLineRunner initDatabase(UserRepositoryMongo userRepository, PasswordEncoder passwordEncoder) {
        return args -> {
            if (userRepository.findByEmail("admin@mail.com").isEmpty()) {
                UserMongoEntity admin = new UserMongoEntity();
                admin.setUserId("ADMIN-01");
                admin.setEmail("admin@mail.com");
                admin.setName("Administrador del Sistema");
                admin.setUsername("admin");
                admin.setPassword(passwordEncoder.encode("admin123"));
                admin.setRole("ADMIN");
                admin.setJoinedAt(LocalDateTime.now());

                userRepository.save(admin);
                System.out.println("### Usuario ADMIN creado con éxito en Atlas ###");
            }
        };
    }
}