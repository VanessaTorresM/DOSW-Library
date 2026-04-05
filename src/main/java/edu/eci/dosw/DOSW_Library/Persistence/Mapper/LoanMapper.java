package edu.eci.dosw.DOSW_Library.Persistence.Mapper;


import edu.eci.dosw.DOSW_Library.Modelo.loan;
import edu.eci.dosw.DOSW_Library.Persistence.Entidades.LoanEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface LoanMapper {

    @Mapping(source = "loanDate", target = "loandate")
    loan toModel(LoanEntity entity);

    @Mapping(source = "loandate", target = "loanDate")
    @Mapping(target = "loanId", ignore = true)
    @Mapping(target = "returnedDate", ignore = true)
    LoanEntity toEntity(loan model);
}
