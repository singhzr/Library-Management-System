package com.example.LMS.Services;

import com.example.LMS.Entities.LibraryCard;
import com.example.LMS.Entities.Student;
import com.example.LMS.Enums.CardStatus;
import com.example.LMS.Repository.CardRepository;
import com.example.LMS.Repository.StudentRepository;
import com.example.LMS.RequestDTOs.AssociateCardStudentRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CardService {
    @Autowired
    private CardRepository cardRepository;
    @Autowired
    private StudentRepository studentRepository;

    public String getFreshCard(){

        LibraryCard newCard = new LibraryCard();
        newCard.setCardStatus(CardStatus.NEW);
        newCard.setNoOfBooksIssued(0);

       LibraryCard savedCard = cardRepository.save(newCard);

        return "New Card with Card Number "+savedCard.getCardId()+" has been generated";
    }

    public String associateCardAndStudent(AssociateCardStudentRequest associateCardStudentRequest)throws Exception{

        Integer cardId = associateCardStudentRequest.getCardId();
        Integer studentId = associateCardStudentRequest.getStudentId();
        Optional<LibraryCard> optionalLibraryCard = cardRepository.findById(cardId);

        if(optionalLibraryCard.isEmpty()){
            throw new Exception("Invalid Card Id entered");
        }
        LibraryCard libraryCard = optionalLibraryCard.get();

        Optional<Student> optionalStudent = studentRepository.findById(studentId);

        if(optionalStudent.isEmpty()){
            throw new Exception("Invalid Student Id entered");
        }

        Student student = optionalStudent.get();

        libraryCard.setCardStatus(CardStatus.ACTIVE);
        libraryCard.setStudent(student); //this makes sure relationship would be established from Library Card side
        libraryCard.setNoOfBooksIssued(0);

        studentRepository.save(student); //Save the parent class this will automatically save the library card
                                        // entity because of bidirectional mapping

        return "Card with cardId "+cardId+" and student with studentId "+studentId+" are associated";
    }

    public String deleteCardByCardId(Integer cardId) throws Exception{

        Optional<LibraryCard> optionalLibraryCard = cardRepository.findById(cardId);

        if(optionalLibraryCard.isEmpty()){
            throw new Exception("Invalid Card Id entered");
        }

        cardRepository.deleteById(cardId);

        return "Card with cardId "+cardId+" has been deleted";
    }
}

//1. The Optional<LibraryCard> is a Java 8 feature that is used to handle situations where a value
//   might be absent, meaning it could be null.
//2. get() method is used to get the object from Optional ds

