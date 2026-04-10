package edu.eci.dosw.DOSW_Library.DTO;

import lombok.Data;

@Data
public class BookDTO {
    private String id;
    private String title;
    private String author;
    private int availableStock;
}
