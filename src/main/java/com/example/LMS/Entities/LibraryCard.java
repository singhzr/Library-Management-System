package com.example.LMS.Entities;
import com.example.LMS.Enums.CardStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "LibraryCard")
public class LibraryCard {

    public static final Integer MAX_NO_OF_ALLOWED_BOOKS = 3;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer cardId;

    @Enumerated(value = EnumType.STRING)
    private CardStatus cardStatus;

    private int noOfBooksIssued;

    @JoinColumn(name = "studentId", referencedColumnName = "studentId")
    @OneToOne
    private Student student;


    @OneToMany(mappedBy = "libraryCard", cascade = CascadeType.ALL)//0.
    public List<Transaction> transactionList = new ArrayList<>();
}

//0. (bidirectional mapping) any changes made in libraryCard entity will make sure that Transaction entity also gets updated

//1. @Enumerated(value = EnumType.STRING) This is an annotation for mysql to understand custom datatype
//    and store it as a string format inside the DB.

// 2. @JoinColumn name = "studentId": Specifies the name of the foreign key column in the LibraryCard table.
//    referencedColumnName = "studentId": Specifies the name of the referenced column in the Student table (primary key).
//    nullable = false: Specifies that the foreign key column cannot contain null values.

//    If you don't specify the @JoinColumn, JPA will use default naming conventions(primary key of parent table)
//    to generate the foreign key column name.

// 3. private Student student this line helps in joining Librarycard and student table on the basis of either primary key
//    of student table by default or on the basis of column mentioned in @JoinColumn
