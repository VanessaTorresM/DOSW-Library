package edu.eci.dosw.DOSW_Library.Persistence.nonrelational.Mapper;

import edu.eci.dosw.DOSW_Library.Modelo.Loan;
import edu.eci.dosw.DOSW_Library.Persistence.nonrelational.Document.LoanMongoEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", implementationName = "LoanMongoMapperImpl", uses = {BookMongoMapper.class, UserMongoMapper.class})
public interface LoanMongoMapper {

    @Mapping(source = "book", target = "book")
    @Mapping(source = "user", target = "user")
    @Mapping(source = "loanDate", target = "loandate")
    Loan toModel(LoanMongoEntity entity);

    @Mapping(source = "book", target = "book")
    @Mapping(source = "user", target = "user")
    @Mapping(source = "loandate", target = "loanDate")
    LoanMongoEntity toEntity(Loan domain);
}
