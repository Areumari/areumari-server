package com.example.jwt.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class LoginResponseDto {
    private TokenDto tokenDto; // JWT 관련 데이터
    private String message; // 환영 메시지
}