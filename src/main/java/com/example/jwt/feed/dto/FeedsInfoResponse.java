package com.example.jwt.feed.dto;

import com.example.jwt.feed.entity.Feed;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
public class FeedsInfoResponse {

    private Long id;
    private String content;
    private List<String> images;

    private FeedsInfoResponse(Long id, String content, List<String> images) {
        this.id = id;
        this.content = content;
        this.images = images;
    }

    public static FeedsInfoResponse of(Feed feed, List<String> images) {
        return new FeedsInfoResponse(feed.getId(), feed.getContent(), images);
    }
}