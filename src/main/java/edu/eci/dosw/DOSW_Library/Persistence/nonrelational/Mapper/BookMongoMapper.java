package edu.eci.dosw.DOSW_Library.Persistence.nonrelational.Mapper;

import edu.eci.dosw.DOSW_Library.Modelo.Book;
import edu.eci.dosw.DOSW_Library.Persistence.nonrelational.Document.BookMongoEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", implementationName = "BookMongoMapperImpl") // <--- AGREGA ESTO
public interface BookMongoMapper {
    Book toModel(BookMongoEntity entity);
    BookMongoEntity toEntity(Book model);
}
