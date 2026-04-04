package edu.eci.dosw.DOSW_Library.Persistence.DTO;

import lombok.Data;

import java.util.Date;

@Data
public class LoanDTO {
    private Long loanId;
    private UserDTO user;
    private BookDTO book;
    private Date loanDate;
    private String status;
}
