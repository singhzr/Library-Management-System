package com.example.LMS.Controllers;

import com.example.LMS.RequestDTOs.IssueBookRequest;
import com.example.LMS.RequestDTOs.ReturnBookRequest;
import com.example.LMS.Services.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/transaction")
public class TransactionController {

    @Autowired
    private TransactionService transactionService;

    @PutMapping("/issueBook")
    public ResponseEntity issueBook(@RequestBody IssueBookRequest issueBookRequest){

        try{
            String result = transactionService.issueBook(issueBookRequest);
            return new ResponseEntity<>(result, HttpStatus.OK);
        }
        catch (Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
    @PutMapping("/returnBook")
    public ResponseEntity returnBook(@RequestBody ReturnBookRequest returnBookRequest){

        try{
            String result = transactionService.returnBook(returnBookRequest);
            return new ResponseEntity<>(result, HttpStatus.OK);
        }
        catch (Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

}
