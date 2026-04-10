package edu.eci.dosw.DOSW_Library.Persistence.nonrelational.Document;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "books")
public class BookMongoEntity {

    @Id
    private String id;
    private String title;
    private String author;
    private String isbn;

    private List<String> categories; // En NoSQL las listas son directas
    private String publicationType; // Revista, Ebook, etc.
    private LocalDateTime publicationDate;
    private LocalDateTime addedAt;

    // Metadata
    private int pages;
    private String language;
    private String publisher;

    // Disponibilidad
    private String status; // Available, Borrowed, etc.
    private int totalCopies;
    private int availableCopies;
    private int borrowedCopies;
}
