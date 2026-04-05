package edu.eci.dosw.DOSW_Library.Persistence.Mapper;


import edu.eci.dosw.DOSW_Library.Modelo.User;

import edu.eci.dosw.DOSW_Library.Persistence.Entidades.UserEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserMapper {

    @Mapping(source = "userId", target = "id")
    User toModel(UserEntity entity);

    @Mapping(source = "id", target = "userId")
    @Mapping(target = "username", ignore = true)
    @Mapping(target = "password", ignore = true)
    @Mapping(target = "role", ignore = true)
    UserEntity toEntity(User model);
}