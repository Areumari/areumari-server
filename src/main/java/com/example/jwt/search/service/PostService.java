package com.example.jwt.search.service;

import com.example.jwt.search.dto.PostDTO;
import com.example.jwt.search.entity.Post;
import com.example.jwt.search.enums.SearchType;
import com.example.jwt.search.exception.PostNotFoundException;
import com.example.jwt.application.dao.PostRepository;
import org.modelmapper.ModelMapper;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
public class PostService {
    private final PostRepository postRepository;
    private final ModelMapper modelMapper;

    public PostService(PostRepository postRepository, ModelMapper modelMapper) {
        this.postRepository = postRepository;
        this.modelMapper = modelMapper;
    }

    @Cacheable(value = "postSearchCache", key = "#searchType.toString() + '_' + #keyword")
    public List<PostDTO> searchPosts(SearchType searchType, String keyword) {
        validateSearchCriteria(searchType, keyword);

        List<Post> posts = switch (searchType) {
            case TITLE -> {
                List<Post> result = postRepository.searchByTitleOrCategory(keyword); // 제목 검색
                if (result.isEmpty()) {
                    throw new PostNotFoundException("No posts found matching title: " + keyword);
                }
                yield result;
            }
            case CATEGORY -> {
                List<Post> result = postRepository.searchByTitleOrCategory(keyword); // 카테고리 검색
                if (result.isEmpty()) {
                    throw new PostNotFoundException("No posts found matching category: " + keyword);
                }
                yield result;
            }
            case BOTH -> {
                List<Post> result = postRepository.searchByTitleOrCategory(keyword); // 제목 + 카테고리 검색
                if (result.isEmpty()) {
                    throw new PostNotFoundException("No posts found matching both title and category: " + keyword);
                }
                yield result;
            }
            case FULLTEXT -> {
                List<Post> result = postRepository.searchByTitleOrCategory(keyword); // Full-Text 검색
                if (result.isEmpty()) {
                    throw new PostNotFoundException("No posts found matching full-text: " + keyword);
                }
                yield result;
            }
            default -> throw new IllegalArgumentException("Unsupported search type: " + searchType);
        };

        return posts.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    private void validateSearchCriteria(SearchType searchType, String keyword) {
        if (searchType == null) {
            throw new IllegalArgumentException("Search type cannot be null");
        }
        if (!StringUtils.hasText(keyword)) {
            throw new IllegalArgumentException("Search keyword cannot be empty");
        }
        if (keyword.length() < 2 || keyword.length() > 50) {
            throw new IllegalArgumentException("Search keyword must be between 2 and 50 characters");
        }
    }

    private PostDTO convertToDTO(Post post) {
        return modelMapper.map(post, PostDTO.class);
    }
}