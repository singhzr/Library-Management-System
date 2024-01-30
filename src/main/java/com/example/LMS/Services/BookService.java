package com.example.LMS.Services;

import com.example.LMS.Entities.Author;
import com.example.LMS.Entities.Book;
import com.example.LMS.Repository.AuthorRepository;
import com.example.LMS.RequestDTOs.AddBookRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class BookService {

    @Autowired
    private AuthorRepository authorRepository;

    public String addBook(AddBookRequest addBookRequest)throws Exception{

        Integer authorId = addBookRequest.getAuthorId();
        Optional<Author> optionalAuthor = authorRepository.findById(authorId);

        if(optionalAuthor.isEmpty()){

            throw new Exception("Author with authorId "+authorId+" does not exist. Hence Book cannot be saved");
        }

        //1. Get the author Entity from the authorId
        Author author = optionalAuthor.get();

        //2. Create the Book Entity from bookRequest
        Book newBook = new Book(addBookRequest.getBookName(),addBookRequest.getGenre(),
                                addBookRequest.getNoOfPages(), addBookRequest.getPrice(), addBookRequest.getPublishDate());

        author.setNumberOfBookWritten(author.getNumberOfBookWritten()+1);

        //3. Adding for the book the Author Entity
        newBook.setAuthor(author);
        newBook.setIsAvailable(1);

        //4. for the Author add the book in the bookList
        author.getBookList().add(newBook);//1.

        //4. Save the parent class this will automatically save the book entity because of bidirectional mapping
        authorRepository.save(author);

        return "Book with Book name "+newBook.getBookName()+ " has been saved to DB";
    }
}

//1. (bidirectional mapping) If you only set the author for the book (newBook.setAuthor(author)), the relationship
//   would be established from the Book side, but the Author side would not be aware of this association.
//   By adding the newBook to the bookList of the Author, the relationship from Author side is also established