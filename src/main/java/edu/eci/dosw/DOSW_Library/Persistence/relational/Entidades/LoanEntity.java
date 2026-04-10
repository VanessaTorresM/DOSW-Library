package edu.eci.dosw.DOSW_Library.Persistence.relational.Entidades;

import jakarta.persistence.*;
import lombok.Data;
import java.util.Date;

@Entity
@Table(name = "loans")
@Data
public class LoanEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private UserEntity user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "book_id", nullable = false)
    private BookEntity book;

    @Temporal(TemporalType.TIMESTAMP)
    private Date loanDate;

    @Temporal(TemporalType.TIMESTAMP)
    private Date returnedDate;

    @Column(nullable = false)
    private String status;
}