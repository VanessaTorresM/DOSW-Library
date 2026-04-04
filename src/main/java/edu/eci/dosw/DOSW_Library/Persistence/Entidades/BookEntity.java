package edu.eci.dosw.DOSW_Library.Persistence.Entidades;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "books")
@Data
public class BookEntity {
    @Id
    @Column(name = "book_id")
    private String bookId;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String author;

    @Column(name = "total_stock", nullable = false)
    private int totalStock;

    @Column(name = "available_stock", nullable = false)
    private int availableStock;
}