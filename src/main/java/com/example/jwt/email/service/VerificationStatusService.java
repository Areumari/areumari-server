package com.example.jwt.email.service;

import com.example.jwt.email.constants.EmailConstants;
import com.example.jwt.email.exception.EmailErrorCode;
import com.example.jwt.email.exception.EmailException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
@RequiredArgsConstructor
public class VerificationStatusService {

    private final StringRedisTemplate redisTemplate;

    // 이메일 인증 완료 상태 저장
    public void markEmailAsVerified(String email) {
        try {
            String key = getVerifiedKey(email);
            redisTemplate.opsForValue().set(key, "true",
                    EmailConstants.VERIFIED_STATUS_EXPIRY_HOURS, TimeUnit.HOURS);
        } catch (Exception e) {
            throw new EmailException(EmailErrorCode.REDIS_OPERATION_FAILED);
        }
    }

    // 이메일 인증 상태 확인
    public boolean isEmailVerified(String email) {
        try {
            String key = getVerifiedKey(email);
            String status = redisTemplate.opsForValue().get(key);
            return "true".equals(status);
        } catch (Exception e) {
            throw new EmailException(EmailErrorCode.REDIS_OPERATION_FAILED);
        }
    }

    // Redis 키 생성
    private String getVerifiedKey(String email) {
        return EmailConstants.REDIS_VERIFIED_PREFIX + email;
    }
}