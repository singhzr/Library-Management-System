package com.example.LMS.Services;

import com.example.LMS.Entities.Student;
import com.example.LMS.RequestDTOs.ModifyPhoneNumberRequest;
import com.example.LMS.Repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class StudentService {

    @Autowired
    private  StudentRepository studentRepository;

    @Autowired
    private JavaMailSender javaMailSender;

    public String addStudent(Student student){

        Student savedStudent = studentRepository.save(student);

        SimpleMailMessage message = new SimpleMailMessage();

        message.setSubject("Hi "+savedStudent.getName()+" !");

        message.setFrom("springacciojob@gmail.com");
        message.setTo(savedStudent.getEmailId());


        message.setText("You have been successfully Registered on our portal.");

        javaMailSender.send(message);

        return "The student has been saved to DB with studentId "+savedStudent.getStudentId();
    }
    public Student findStudentById(Integer studentId)throws Exception{

        Optional<Student> optionalStudent = studentRepository.findById(studentId);

        if(optionalStudent.isEmpty()){
            throw new Exception("Student with Id "+studentId+" does not exist");
        }
        Student student = optionalStudent.get();

        return student;
    }
    public String deleteStudentByStudentId(Integer studentId) throws Exception{

        Optional<Student> optionalStudent = studentRepository.findById(studentId);

        if(optionalStudent.isEmpty()){
            throw new Exception("Invalid Student Id entered");
        }

        studentRepository.deleteById(studentId);

        return "Student with studentId "+studentId+" has been deleted";
    }
    public String modifyPhoneNumber(ModifyPhoneNumberRequest modifyPhoneNumberRequest)throws Exception{

        Integer studentId = modifyPhoneNumberRequest.getStudentId();
        String newPhoneNumber = modifyPhoneNumberRequest.getNewPhoneNumber();

        Optional<Student> optionalStudent = studentRepository.findById(studentId);

        if(optionalStudent.isEmpty()){

            throw new Exception("Student with studentId "+studentId+" does not exist");
        }
        Student student = optionalStudent.get();

        student.setPhoneNo(newPhoneNumber);
        studentRepository.save(student);

        return "Phone Number for studentId "+studentId+" has been modified";
    }
}
