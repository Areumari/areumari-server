package com.example.jwt.feed.controller;

import com.example.jwt.feed.dto.GetFeedsResponse;
import com.example.jwt.feed.service.FeedService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/feeds")
@RequiredArgsConstructor
@Tag(name = "Feed API", description = "피드 관련 API") // Swagger 그룹 이름
public class FeedController {

    private final FeedService feedService;

    @Operation(summary = "피드 조회", description = "특정 방에서 사용자의 피드를 조회합니다.")
    @GetMapping
    public ResponseEntity<GetFeedsResponse> getFeeds(
            @Parameter(description = "사용자 이메일", required = true) @RequestParam String userEmail,
            @Parameter(description = "방 ID", required = true) @RequestParam Long roomId,
            @Parameter(description = "가져올 피드 개수", required = true) @RequestParam int size,
            @Parameter(description = "마지막 피드 ID (페이징)", required = false) @RequestParam(required = false) Long lastFeedId) {

        GetFeedsResponse response = feedService.getFeeds(userEmail, roomId, size, lastFeedId);
        return ResponseEntity.ok(response);
    }
}
