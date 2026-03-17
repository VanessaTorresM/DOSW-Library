package edu.eci.dosw.DOSW_Library.Modelo;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;


@Data
@Getter
@Setter

public class Book {
    private String Id;
    private String author;
    private String title;
}
