package edu.eci.dosw.DOSW_Library.Persistence.Mapper;

import edu.eci.dosw.DOSW_Library.Modelo.Book;
import edu.eci.dosw.DOSW_Library.Persistence.Entidades.BookEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface BookMapper {

    @Mapping(source = "bookId", target = "id")
    Book toModel(BookEntity entity);

    @Mapping(source = "id", target = "bookId")
    @Mapping(target = "availableStock", ignore = true)
    @Mapping(target = "totalStock", ignore = true)
    BookEntity toEntity(Book model);
}
