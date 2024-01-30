package com.example.LMS.Entities;

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
@Table(name = "Author")
public class Author {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer authorId;

    private String authorName;

    private int authorAge;

    @Column(unique = true, nullable = false)
    private String emailId;

    private int numberOfBookWritten;

    @OneToMany(mappedBy = "author", cascade = CascadeType.ALL)//0.
    private List<Book> bookList = new ArrayList<>();

    public Author(String authorName, int authorAge, String emailId) {
        this.authorName = authorName;
        this.authorAge = authorAge;
        this.emailId = emailId;
    }
}

//0. any changes made in author entity will make sure that book entity also gets updated

//1. The @GeneratedValue annotation in JPA (Java Persistence API) is used to specify how the primary key
//   for an entity should be generated.

//2. The (strategy = GenerationType.IDENTITY) part of the @GeneratedValue annotation specifies the strategy
//   for generating primary key values for the annotated field. In this case, the strategy is set to GenerationType.IDENTITY.

//   GenerationType.IDENTITY: This strategy indicates that the database should automatically generate
//   unique primary key values for the annotated field.

//3. @Column(unique = true, nullable = false) indicates that this column will have only unique values and cannot contain
//    null values

//4. @OneToMany(mappedBy = "author"(child table FK variable name), cascade = CascadeType.ALL):
//   Defines a one-to-many relationship between Author and Book entities. The mappedBy attribute("author")
//   indicates the field in the Book class that owns the relationship.
//   The cascade attribute specifies that any operations (e.g., deletion) on an Author should be cascaded to
//   its associated Book entities. Suppose you delete any data from author table then it will also get deleted
//   from book table