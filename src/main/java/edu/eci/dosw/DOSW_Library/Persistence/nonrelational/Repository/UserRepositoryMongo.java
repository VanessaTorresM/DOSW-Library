package edu.eci.dosw.DOSW_Library.Persistence.nonrelational.Repository;

import edu.eci.dosw.DOSW_Library.Persistence.nonrelational.Document.UserMongoEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import java.util.Optional;

public interface UserRepositoryMongo extends MongoRepository<UserMongoEntity, String> {
    Optional<UserMongoEntity> findByEmail(String email);

    Optional<UserMongoEntity> findByUsername(String username);
}
