package com.example.jwt.feed.service;

import com.example.jwt.application.dao.FeedRepository;
import com.example.jwt.feed.dto.GetFeedsResponse;
import com.example.jwt.feed.dto.FeedImageCollection;
import com.example.jwt.feed.entity.Feed;
import com.example.jwt.feed.pagination.ScrollPaginationCollection;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FeedService {

    private final FeedRepository feedRepository;

    public GetFeedsResponse getFeeds(String userEmail, Long roomId, int size, Long lastFeedId) {
        PageRequest pageRequest = PageRequest.of(0, size + 1);
        List<Feed> feeds = feedRepository.findAllByRoomIdAndIdLessThanOrderByIdDesc(roomId, lastFeedId, pageRequest);

        ScrollPaginationCollection<Feed> feedsCursor = ScrollPaginationCollection.of(feeds, size);
        return GetFeedsResponse.of(feedsCursor, FeedImageCollection.of(feeds), feedRepository.countByRoomId(roomId));
    }
}
