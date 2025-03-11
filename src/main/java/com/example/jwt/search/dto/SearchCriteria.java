package com.example.jwt.search.dto;

import com.example.jwt.search.enums.SearchType;
import jakarta.validation.constraints.NotBlank;

public class SearchCriteria {

    @NotBlank
    private String searchType;

    @NotBlank
    private String keyword;

    // Getter와 Setter

    public SearchType getSearchType() {
        return SearchType.valueOf(searchType.toUpperCase());
    }

    public void setSearchType(String searchType) {
        this.searchType = searchType;
    }

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }
}
