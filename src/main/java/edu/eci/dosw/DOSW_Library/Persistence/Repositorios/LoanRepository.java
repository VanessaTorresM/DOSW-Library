package edu.eci.dosw.DOSW_Library.Persistence.Repositorios;


import edu.eci.dosw.DOSW_Library.Persistence.Entidades.LoanEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.Optional;

public interface LoanRepository extends JpaRepository<LoanEntity, Long> {
    List<LoanEntity> findByUser_UserId(String userId);
    Optional<LoanEntity> findByIdAndStatus(Long id, String status);
}
