package com.example.LMS.RequestDTOs;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class IssueBookRequest {
    private Integer bookId;
    private Integer cardId;
}
