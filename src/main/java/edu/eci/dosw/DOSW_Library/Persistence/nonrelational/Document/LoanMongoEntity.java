package edu.eci.dosw.DOSW_Library.Persistence.nonrelational.Document;

import edu.eci.dosw.DOSW_Library.Modelo.LoanStatus;
import edu.eci.dosw.DOSW_Library.Modelo.LoanStatusHistory;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import java.util.Date;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "loans")
public class LoanMongoEntity {
    @Id
    private String id;

    private UserMongoEntity user;
    private BookMongoEntity book;

    private Date loanDate;
    private Date returnedDate;

    // Usamos el Enum aquí
    private LoanStatus status;

    // El historial que pide el PDF
    private List<LoanStatusHistory> history;
}
