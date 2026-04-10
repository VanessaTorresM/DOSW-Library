package edu.eci.dosw.DOSW_Library.Persistence.relational.Repositorios;

import edu.eci.dosw.DOSW_Library.Persistence.relational.Entidades.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface UserRepository extends JpaRepository<UserEntity, String> {
    Optional<UserEntity> findByUsername(String username);
}
