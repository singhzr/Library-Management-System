package com.example.LMS.RequestDTOs;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ReturnBookRequest {
    private Integer bookId;
    private Integer cardId;
}
