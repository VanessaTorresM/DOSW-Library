package edu.eci.dosw.DOSW_Library.Util;

import edu.eci.dosw.DOSW_Library.Persistence.Entidades.UserEntity;
import edu.eci.dosw.DOSW_Library.Persistence.Repositorios.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class DataInitializer {

    @Bean
    CommandLineRunner initDatabase(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        return args -> {
            // Revisamos si ya existe un admin para no duplicarlo
            if (userRepository.findByUsername("admin").isEmpty()) {
                UserEntity admin = new UserEntity();
                admin.setUserId("ADMIN-01");
                admin.setUsername("admin");
                admin.setPassword(passwordEncoder.encode("admin123"));
                admin.setRole("ADMIN");

                userRepository.save(admin);
                System.out.println("### Usuario ADMIN creado por defecto (admin / admin123) ###");
            }
        };
    }
}
