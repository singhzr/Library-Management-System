package com.example.LMS.Controllers;

import com.example.LMS.Entities.Author;
import com.example.LMS.RequestDTOs.AddAuthorRequest;
import com.example.LMS.RequestDTOs.AddBookRequest;
import com.example.LMS.Services.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("author")
public class AuthorController {

    @Autowired
    private AuthorService authorService;

    @PostMapping("/addAuthor")
    public String addAuthor(@RequestBody AddAuthorRequest addAuthorRequest){

        String result = authorService.addAuthor(addAuthorRequest);

        return result;
    }
    @PutMapping("/deleteAuthorByAuthorId")
    public ResponseEntity deleteAuthorByAuthorId(@RequestParam("authorId") Integer authorId){

        try{
            String result = authorService.deleteAuthorByAuthorId(authorId);

            return new ResponseEntity(result, HttpStatus.OK);
        }
        catch (Exception e){

            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}
