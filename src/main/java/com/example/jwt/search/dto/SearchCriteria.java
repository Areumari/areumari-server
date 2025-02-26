package com.example.jwt.search.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;
import com.example.jwt.search.enums.SearchType;

@Data
public class SearchCriteria {
    private SearchType searchType;

    @NotBlank(message = "Search keyword cannot be empty")
    @Size(min = 2, max = 50, message = "Search keyword must be between 2 and 50 characters")
    private String keyword;
}