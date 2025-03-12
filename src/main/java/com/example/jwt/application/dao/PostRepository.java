package com.example.jwt.application.dao;

import com.example.jwt.search.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {

    @Query(value = """
        SELECT * FROM posts 
        WHERE MATCH(title, category) AGAINST (:keyword IN BOOLEAN MODE)
        ORDER BY created_at DESC
        """, nativeQuery = true)
    List<Post> searchByTitleOrCategory(@Param("keyword") String keyword);
}