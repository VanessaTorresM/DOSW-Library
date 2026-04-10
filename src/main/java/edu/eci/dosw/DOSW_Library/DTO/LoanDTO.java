package edu.eci.dosw.DOSW_Library.DTO;

import lombok.Data;

import java.util.Date;

@Data
public class LoanDTO {
    private Long id;
    private UserDTO user;
    private BookDTO book;
    private Date loanDate;
    private String status;
}
