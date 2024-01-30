package com.example.LMS.Services;

import com.example.LMS.Entities.Book;
import com.example.LMS.Entities.LibraryCard;
import com.example.LMS.Entities.Transaction;
import com.example.LMS.Enums.TransactionStatus;
import com.example.LMS.Enums.TransactionType;
import com.example.LMS.Exceptions.BookNotAvailableException;
import com.example.LMS.Exceptions.BookNotFoundException;
import com.example.LMS.Exceptions.CardNotFoundException;
import com.example.LMS.Exceptions.MaxLimitReachedException;
import com.example.LMS.Repository.BookRepository;
import com.example.LMS.Repository.CardRepository;
import com.example.LMS.Repository.TransactionRepository;
import com.example.LMS.RequestDTOs.IssueBookRequest;
import com.example.LMS.RequestDTOs.ReturnBookRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;

@Service
public class TransactionService {

    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private CardRepository cardRepository;
    public String issueBook(IssueBookRequest issueBookRequest)throws Exception{

        Transaction transaction = new Transaction();
        transaction.setTransactionStatus(TransactionStatus.ONGOING);
        transaction.setTransactionType(TransactionType.ISSUE);
        transaction.setCreatedOn(new Date());

        Integer bookId = issueBookRequest.getBookId();
        Integer cardId = issueBookRequest.getCardId();

        Optional<Book> optionalBook = bookRepository.findById(bookId);
        Optional<LibraryCard> optionalLibraryCard = cardRepository.findById(cardId);

        if(optionalBook.isEmpty()){
            throw new BookNotFoundException("Book with bookId "+bookId+" does not exist");
        }
        if(optionalLibraryCard.isEmpty()){
            throw new CardNotFoundException("Library card with cardId "+cardId+" does not exist");
        }
        //1. Get the book and card Entity from DB
        Book book = optionalBook.get();
        LibraryCard libraryCard = optionalLibraryCard.get();

        //2. Check for availability
        if(book.getIsAvailable()==0){
            transaction.setTransactionStatus(TransactionStatus.FAILURE);
            transaction = transactionRepository.save(transaction);
            throw new BookNotAvailableException("Book with bookId "+bookId+" is not available. TransactionId is "+transaction.getTransactionId());
        }
        //3. Check for max book issued
        if(libraryCard.getNoOfBooksIssued() >= LibraryCard.MAX_NO_OF_ALLOWED_BOOKS){
            transaction.setTransactionStatus(TransactionStatus.FAILURE);
            transaction = transactionRepository.save(transaction);
            throw new MaxLimitReachedException("You have reached the max limit of issed books. Please return a book in order to issue new book"
                              + " Transaction Id is "+transaction.getTransactionId());
        }

        //If you have reached that means all validations are OK

        transaction.setTransactionStatus(TransactionStatus.SUCCESS);

        //Child class also needs to have the attributes of parent class
        transaction.setBook(book);
        transaction.setLibraryCard(libraryCard);


        transaction.setTransactionType(TransactionType.ISSUE);
        //4. update the card and the book status
        book.setIsAvailable(0);

        libraryCard.setNoOfBooksIssued(libraryCard.getNoOfBooksIssued()+1);

        //5. Save the child as it will cascade to both of the Parents
        transaction = transactionRepository.save(transaction);

        return "The transaction with Id "+transaction.getTransactionId()+" has been saved to the DB";
    }


    public String returnBook(ReturnBookRequest returnBookRequest)throws Exception{

        Transaction transaction = new Transaction();
        transaction.setTransactionStatus(TransactionStatus.ONGOING);
        transaction.setTransactionType(TransactionType.RETURN);
        transaction.setCreatedOn(new Date());

        Integer bookId = returnBookRequest.getBookId();
        Integer cardId = returnBookRequest.getCardId();

        Optional<Book> optionalBook = bookRepository.findById(bookId);
        Optional<LibraryCard> optionalLibraryCard = cardRepository.findById(cardId);

        if(optionalBook.isEmpty()){
            throw new Exception("Book with bookId "+bookId+" does not exist");
        }
        if(optionalLibraryCard.isEmpty()){
            throw new Exception("Library card with cardId "+cardId+" does not exist");
        }
        //1. Get the book and card Entity from DB
        Book book = optionalBook.get();
        LibraryCard libraryCard = optionalLibraryCard.get();

        if(book.getIsAvailable() == 1){

            throw new Exception("Book with bookId "+book.getBookId()+ " is already present");
        }
        transaction.setTransactionStatus(TransactionStatus.SUCCESS);

        transaction.setBook(book);
        transaction.setLibraryCard(libraryCard);

        transaction.setTransactionType(TransactionType.RETURN);
        //4. update the card and the book status
        book.setIsAvailable(1);

        libraryCard.setNoOfBooksIssued(libraryCard.getNoOfBooksIssued()-1);

        //5. Save the child as it will cascade to both of the Parents
        transaction = transactionRepository.save(transaction);

        return "The transaction with Id "+transaction.getTransactionId()+" has been saved to the DB";

    }
}
