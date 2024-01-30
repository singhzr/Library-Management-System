package com.example.LMS.Services;

import com.example.LMS.Entities.Author;
import com.example.LMS.Repository.AuthorRepository;
import com.example.LMS.RequestDTOs.AddAuthorRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthorService {

    @Autowired
    private AuthorRepository authorRepository;

    @Autowired
    private JavaMailSender javaMailSender; //JavaMailSender is a inbuilt class which is used to send mails

    public String addAuthor(AddAuthorRequest addAuthorRequest){

        Author authorEntity = new Author(addAuthorRequest.getAuthorName(),
                                         addAuthorRequest.getAuthorAge(), addAuthorRequest.getEmailId());

        Author savedAuthor = authorRepository.save(authorEntity);

        SimpleMailMessage message = new SimpleMailMessage();

        message.setSubject("Hi "+savedAuthor.getAuthorName()+" !");

        message.setFrom("springacciojob@gmail.com");
        message.setTo(savedAuthor.getEmailId());


        message.setText("You have been successfully Registered on our portal. " +
                "Looking forward for adding more books. ");
        javaMailSender.send(message);

        return "Author with authorId "+savedAuthor.getAuthorId()+" has been saved to DB";
    }

    public String deleteAuthorByAuthorId(Integer authorId)throws Exception {

        Optional<Author> optionalAuthor = authorRepository.findById(authorId);

        if(optionalAuthor.isEmpty()){

            throw new Exception("Author with authorId "+authorId+" does not exist");
        }
        authorRepository.deleteById(authorId);

        return "Author with authorId "+authorId+" has been deleted";
    }
}
