package edu.eci.dosw.DOSW_Library.Persistence.nonrelational.Repository;

import edu.eci.dosw.DOSW_Library.Persistence.nonrelational.Document.LoanMongoEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface LoanRepositoryMongo extends MongoRepository<LoanMongoEntity, String> {
    List<LoanMongoEntity> findByUser_UserId(String userId);
}
