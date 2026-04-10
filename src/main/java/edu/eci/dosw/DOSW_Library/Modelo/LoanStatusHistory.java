package edu.eci.dosw.DOSW_Library.Modelo;

import lombok.*;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class LoanStatusHistory {
    private LoanStatus status;
    private Date createdAt;
}
