package com.example.LMS.RequestDTOs;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AssociateCardStudentRequest {

    private Integer studentId;
    private Integer cardId;
}
