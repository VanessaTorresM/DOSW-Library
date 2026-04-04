package edu.eci.dosw.DOSW_Library.Persistence.Mapper;


import edu.eci.dosw.DOSW_Library.Modelo.loan;
import edu.eci.dosw.DOSW_Library.Persistence.Entidades.LoanEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = {UserMapper.class, BookMapper.class})
public interface LoanMapper {
    loan toModel(LoanEntity entity);

    LoanEntity toEntity(loan model);
}
