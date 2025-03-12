package com.example.jwt.feed.dto;

import com.example.jwt.feed.entity.Feed;
import lombok.Getter;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Getter
public class FeedImageCollection {

    private final Map<Long, List<String>> feedImageMap;

    private FeedImageCollection(Map<Long, List<String>> feedImageMap) {
        this.feedImageMap = feedImageMap;
    }

    public static FeedImageCollection of(List<Feed> feeds) {
        Map<Long, List<String>> map = feeds.stream()
                .collect(Collectors.toMap(Feed::getId, feed -> List.of("image1.jpg", "image2.jpg"))); // 더미 데이터
        return new FeedImageCollection(map);
    }

    public List<String> getImagesByFeedId(Long feedId) {
        return feedImageMap.getOrDefault(feedId, List.of());
    }
}