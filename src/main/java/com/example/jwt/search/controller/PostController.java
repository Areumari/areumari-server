package com.example.jwt.search.controller;

import com.example.jwt.search.dto.ApiResponse;
import com.example.jwt.search.dto.PostDTO;
import com.example.jwt.search.dto.SearchCriteria;
import com.example.jwt.search.service.PostService;
import com.example.jwt.search.exception.PostNotFoundException;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/posts")
@Validated
public class PostController {
    private final PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    @GetMapping("/search")
    public ResponseEntity<ApiResponse<List<PostDTO>>> searchPosts(
            @Valid SearchCriteria searchCriteria) {
        List<PostDTO> results = postService.searchPosts(
                searchCriteria.getSearchType(),
                searchCriteria.getKeyword()
        );
        return ResponseEntity.ok(ApiResponse.success(results));
    }

    @ExceptionHandler(PostNotFoundException.class)
    public ResponseEntity<ApiResponse<Void>> handlePostNotFoundException(PostNotFoundException ex) {
        return ResponseEntity.ok(ApiResponse.error(ex.getMessage()));
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ApiResponse<Void>> handleIllegalArgumentException(IllegalArgumentException ex) {
        return ResponseEntity.ok(ApiResponse.error(ex.getMessage()));
    }
}