package com.example.LMS.Entities;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "Student")
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer studentId;

    private String name;

    private  String branch;

    private double cgpa;

    private String phoneNo;

    private String emailId;

    @OneToOne(mappedBy = "student", cascade = CascadeType.ALL)
    private LibraryCard libraryCard;
}

// 1. @Entity: Indicates that this class represents a table in the database.
//
// 2. @Table(name = "student"): Specifies the name of the table in database. If you don't specify name then
//     class name is taken as table name
//
// 3. @Id: Denotes the primary key field of the entity.
//
// 4. @Getter and @Setter: Lombok annotations that automatically generate getter and setter methods for
//    the class fields.
//
// 5. @NoArgsConstructor and @AllArgsConstructor: Lombok annotations generating a no-argument constructor
//    and an all-argument constructor, respectively.

// 6. The fields(studentRollNo, name, etc.) in your Student class correspond to columns in the "student" table

// 7. The jakarta.persistence package is part of the Jakarta Persistence API (JPA), which is a Java specification
//    for managing relational data in Java applications. It provides a set of Java APIs for standardizing
//    object-relational mapping (ORM) and database operations.