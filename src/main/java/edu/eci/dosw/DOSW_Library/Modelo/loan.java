package edu.eci.dosw.DOSW_Library.Modelo;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;


@Data
@Getter
@Setter
public class loan {
    private Book book;
    private User user;
    private Date loandate;
    private String status;

    public void setLoanDate(Date date) {
    }

}
