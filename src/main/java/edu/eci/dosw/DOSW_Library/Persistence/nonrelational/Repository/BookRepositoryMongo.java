package edu.eci.dosw.DOSW_Library.Persistence.nonrelational.Repository;

import edu.eci.dosw.DOSW_Library.Persistence.nonrelational.Document.BookMongoEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface BookRepositoryMongo extends MongoRepository<BookMongoEntity, String> {
    List<BookMongoEntity> findByAvailableCopiesGreaterThan(int amount);

}
