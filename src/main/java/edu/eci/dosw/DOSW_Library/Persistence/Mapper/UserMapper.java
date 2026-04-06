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
    @Mapping(source = "name", target = "name")
    @Mapping(source = "username", target = "username")
    @Mapping(source = "password", target = "password")
    @Mapping(source = "role", target = "role")
    UserEntity toEntity(User model);
}