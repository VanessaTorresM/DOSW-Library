package edu.eci.dosw.DOSW_Library.Modelo;
import lombok.*;


@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Book {
    private String Id;
    private String author;
    private String title;
}
