package com.example.jwt.feed.dto;

import com.example.jwt.feed.entity.Feed;
import com.example.jwt.feed.pagination.ScrollPaginationCollection;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@ToString
@Getter
@NoArgsConstructor
public class GetFeedsResponse {

    private static final long LAST_CURSOR = -1L;

    private List<FeedsInfoResponse> contents = new ArrayList<>();
    private long totalElements;
    private long nextCursor;

    private GetFeedsResponse(List<FeedsInfoResponse> contents, long totalElements, long nextCursor) {
        this.contents = contents;
        this.totalElements = totalElements;
        this.nextCursor = nextCursor;
    }

    public static GetFeedsResponse of(ScrollPaginationCollection<Feed> feedsScroll, FeedImageCollection feedImages, long totalElements) {
        long nextCursor = (feedsScroll.getNextCursor() != null) ? feedsScroll.getNextCursor().getId() : LAST_CURSOR;
        return new GetFeedsResponse(getContents(feedsScroll.getCurrentScrollItems(), feedImages), totalElements, nextCursor);
    }


    private static List<FeedsInfoResponse> getContents(List<Feed> feedsScroll, FeedImageCollection feedImages) {
        return feedsScroll.stream()
                .map(feed -> FeedsInfoResponse.of(feed, feedImages.getImagesByFeedId(feed.getId())))
                .toList();
    }
}