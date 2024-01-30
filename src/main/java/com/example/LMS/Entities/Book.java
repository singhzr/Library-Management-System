package com.example.LMS.Entities;

import com.example.LMS.Enums.Genre;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "book")
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int bookId;

    @Column(unique = true)
    private String bookName;

    @Enumerated(value = EnumType.STRING)
    private Genre genre;

    private int noOfPages;

    private int price;

    private int isAvailable;

    private Date publishDate;

    @JoinColumn(name = "author_id", referencedColumnName = "authorId")
    @ManyToOne
    private Author author;


    @OneToMany(mappedBy = "book", cascade = CascadeType.ALL)
    public List<Transaction> transactionList = new ArrayList<>();

    public Book(String bookName, Genre genre, int noOfPages, int price, Date publishDate) {
        this.bookName = bookName;
        this.genre = genre;
        this.noOfPages = noOfPages;
        this.price = price;
        this.publishDate = publishDate;
    }
}

// 1. @Enumerated(value = EnumType.STRING): Indicates that the bookGenre field is an enumerated type (enum)
//    and specifies that the values should be stored as strings in the database.

// 2. @ManyToOne: Specifies a Many-to-One relationship between the Book entity and the Author entity.
//    This means that many books can be associated with one author.