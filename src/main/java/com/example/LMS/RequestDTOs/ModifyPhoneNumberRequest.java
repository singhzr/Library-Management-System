package com.example.LMS.RequestDTOs;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ModifyPhoneNumberRequest {

    private Integer studentId;
    private String newPhoneNumber;
}
