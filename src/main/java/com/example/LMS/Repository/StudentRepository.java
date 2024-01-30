package com.example.LMS.Repository;
import com.example.LMS.Entities.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepository extends JpaRepository<Student, Integer> {// we have to pass entity and datatype of pk

}

//Hibernate contains this JpaRepository.

//In the JpaRepository there are inbuilt methods for inserting, deleting, updating, etc. which
//we can call to get our tasks executed. In this way hibernate allows to simply execute SQL queries without making
//us to write those SQL queries we just have to call those methods.
