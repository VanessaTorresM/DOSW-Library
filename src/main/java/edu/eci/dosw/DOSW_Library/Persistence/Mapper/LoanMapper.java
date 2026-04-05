package edu.eci.dosw.DOSW_Library.Persistence.Mapper;


import edu.eci.dosw.DOSW_Library.Modelo.Loan;
import edu.eci.dosw.DOSW_Library.Persistence.Entidades.LoanEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {BookMapper.class, UserMapper.class})
public interface LoanMapper {

    @Mapping(source = "book", target = "book")
    @Mapping(source = "user", target = "user")
    @Mapping(source = "loanDate", target = "loandate")
    Loan toModel(LoanEntity entity);

    @Mapping(source = "book", target = "book")
    @Mapping(source = "user", target = "user")
    @Mapping(source = "loandate", target = "loanDate")
    LoanEntity toEntity(Loan domain);
}
