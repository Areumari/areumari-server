package com.example.jwt.email.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum EmailErrorCode {
    EMAIL_SEND_FAILED("이메일 전송에 실패했습니다."),
    INVALID_VERIFICATION_CODE("유효하지 않은 인증번호입니다."),
    REDIS_OPERATION_FAILED("인증 정보 처리 중 오류가 발생했습니다.");

    private final String message;
}