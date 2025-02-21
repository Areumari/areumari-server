package com.example.jwt.application.dao;

import com.example.jwt.search.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.List;
import java.util.Optional;

public interface PostRepository extends JpaRepository<Post, Long> {
    Optional<List<Post>> findByTitleContainingIgnoreCaseOrderByCreatedAtDesc(String title);
    Optional<List<Post>> findByCategoryContainingIgnoreCaseOrderByCreatedAtDesc(String category);

    @Query("SELECT p FROM Post p WHERE LOWER(p.title) LIKE LOWER(CONCAT('%', :keyword, '%')) " +
            "OR LOWER(p.category) LIKE LOWER(CONCAT('%', :keyword, '%')) " +
            "ORDER BY p.createdAt DESC")
    Optional<List<Post>> searchByTitleOrCategory(@Param("keyword") String keyword);
}