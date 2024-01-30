package com.example.LMS.Entities;

import com.example.LMS.Enums.TransactionStatus;
import com.example.LMS.Enums.TransactionType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

@Entity
@Table(name = "Transaction")
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String transactionId;

    @Enumerated(value = EnumType.STRING)
    private TransactionStatus transactionStatus;

    @CreatedDate
    private Date createdOn;

    private int fineAmount;

    @Enumerated(value = EnumType.STRING)
    private TransactionType transactionType;

    @JoinColumn(name = "cardId", referencedColumnName = "cardId")
    @ManyToOne
    private LibraryCard libraryCard;

    @JoinColumn(name = "bookId", referencedColumnName = "bookId")
    @ManyToOne
    private Book book;
}
