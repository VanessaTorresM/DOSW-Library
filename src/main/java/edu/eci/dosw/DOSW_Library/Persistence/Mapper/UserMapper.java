package edu.eci.dosw.DOSW_Library.Persistence.Mapper;


import edu.eci.dosw.DOSW_Library.Modelo.User;

import edu.eci.dosw.DOSW_Library.Persistence.Entidades.UserEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {
    User toModel(UserEntity entity);
    UserEntity toEntity(User model);
}