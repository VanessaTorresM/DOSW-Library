package edu.eci.dosw.DOSW_Library.Persistence.Repositorios;


import edu.eci.dosw.DOSW_Library.Persistence.Entidades.BookEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookRepository extends JpaRepository<BookEntity, String> {
    List<BookEntity> findByAvailableStockGreaterThan(int amount);
}
