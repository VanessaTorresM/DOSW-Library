package edu.eci.dosw.DOSW_Library.Persistence.nonrelational.Mapper;

import edu.eci.dosw.DOSW_Library.Modelo.User;
import edu.eci.dosw.DOSW_Library.Persistence.nonrelational.Document.UserMongoEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", implementationName = "UserMongoMapperImpl")
public interface UserMongoMapper {

    @Mapping(source = "userId", target = "id")
    User toModel(UserMongoEntity entity);

    @Mapping(source = "id", target = "userId")
    UserMongoEntity toEntity(User model);
}
