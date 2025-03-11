package com.example.jwt.mypage.controller;

import com.example.jwt.mypage.dto.MyPageResponseDto;
import com.example.jwt.mypage.service.MemberService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/mypage")
public class MemberController {
    private final MemberService memberService;

    @Operation(
            summary = "마이페이지 조회",
            description = "현재 로그인한 사용자의 정보를 반환합니다.",
            security = @SecurityRequirement(name = "Bearer Authentication") // 🔹 Swagger에서 JWT 필요하도록 설정
    )
    @GetMapping
    public MyPageResponseDto getMyPage() {
        return memberService.getMyPage();
    }
}
