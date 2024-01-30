package com.example.LMS.Controllers;

import com.example.LMS.RequestDTOs.ModifyPhoneNumberRequest;
import com.example.LMS.Services.StudentService;
import com.example.LMS.Entities.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/student")
public class StudentController {

    @Autowired
    private StudentService studentService;

    @PostMapping("/addStudent")
    public ResponseEntity addStudent(@RequestBody Student student){

        String result = studentService.addStudent(student);

        return new ResponseEntity<>(result, HttpStatus.CREATED);
    }

    @GetMapping("/findById")
    public ResponseEntity findStudentById(@RequestParam("studentId") Integer studentId){

        try{
            Student student = studentService.findStudentById(studentId);
            return new ResponseEntity(student, HttpStatus.OK);
        }
        catch (Exception e) {

            return new ResponseEntity(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }
    @PutMapping("/deleteStudentByStudentId")
    public ResponseEntity deleteStudentByStudentId(@RequestParam("studentId")Integer studentId){

        try{
            String result = studentService.deleteStudentByStudentId(studentId);
            return new ResponseEntity(result, HttpStatus.OK);
        }
        catch (Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
    @PutMapping("modifyPhoneNumber")
    public ResponseEntity modifyPhoneNumber(@RequestBody ModifyPhoneNumberRequest modifyPhoneNumberRequest){

        try{
            String result = studentService.modifyPhoneNumber(modifyPhoneNumberRequest);
            return new ResponseEntity(result, HttpStatus.OK);
        }
        catch (Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}
