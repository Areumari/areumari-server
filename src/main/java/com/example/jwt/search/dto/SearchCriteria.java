package com.example.jwt.search.dto;

import com.example.jwt.search.enums.SearchType;
import jakarta.validation.constraints.NotBlank;

public class SearchCriteria {
    private SearchType searchType;

    @NotBlank
    private String keyword;

    // Getters and setters
    public SearchType getSearchType() {
        return searchType;
    }

    public void setSearchType(SearchType searchType) {
        this.searchType = searchType;
    }

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }
}
