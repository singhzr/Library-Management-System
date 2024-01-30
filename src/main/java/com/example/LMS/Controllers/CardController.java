package com.example.LMS.Controllers;

import com.example.LMS.RequestDTOs.AssociateCardStudentRequest;
import com.example.LMS.Services.CardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/card")
public class CardController {
    @Autowired
    private CardService cardService;

    @PostMapping("/generateACard")
    public String addCard(){

        String result = cardService.getFreshCard();

        return result;
    }

    @PutMapping("/associateCardAndStudent")
    public ResponseEntity associateCardAndStudent(@RequestBody AssociateCardStudentRequest associateCardStudentRequest){

        try {
            String result = cardService.associateCardAndStudent(associateCardStudentRequest);
            return new ResponseEntity(result,HttpStatus.OK);
        }
        catch (Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
    @PutMapping("/deleteCardByCardId")
    public ResponseEntity deleteCardByCardId(@RequestParam("cardId")Integer cardId){

        try{
            String result = cardService.deleteCardByCardId(cardId);
            return new ResponseEntity(result, HttpStatus.OK);
        }
        catch (Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}
//1. The use of ResponseEntity without explicitly specifying a type parameter is allowed in Java, and
//   the compiler can infer the type based on the actual return values
//   ResponseEntity contains a return type value such as String etc. and an HTTP status
//   The benefit of using ResponseEntity is that you can also send status code