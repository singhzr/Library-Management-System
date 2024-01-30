package com.example.LMS.RequestDTOs;

import com.example.LMS.Enums.Genre;
import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class AddBookRequest {

    private String bookName;

    private Genre genre;

    private int noOfPages;

    private int price;

    private Date publishDate;

    private int authorId;
}
