package edu.eci.dosw.DOSW_Library.Persistence.Mapper;

import edu.eci.dosw.DOSW_Library.Modelo.Book;
import edu.eci.dosw.DOSW_Library.Persistence.Entidades.BookEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface BookMapper {

    @Mapping(source = "availableStock", target = "stock")
    Book toModel(BookEntity entity);

    @Mapping(source = "stock", target = "availableStock")
    BookEntity toEntity(Book model);
}
