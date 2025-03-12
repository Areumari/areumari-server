package com.example.jwt.application.dao;

import com.example.jwt.feed.entity.Feed;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FeedRepository extends JpaRepository<Feed, Long> {
    List<Feed> findAllByRoomIdAndIdLessThanOrderByIdDesc(Long roomId, Long lastFeedId, Pageable pageable);

    long countByRoomId(Long roomId);
}