package com.example.jwt.email.service;

import com.example.jwt.email.constants.EmailConstants;
import com.example.jwt.email.exception.EmailErrorCode;
import com.example.jwt.email.exception.EmailException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.Random;
import java.util.concurrent.TimeUnit;

@Service
@RequiredArgsConstructor
public class VerificationCodeService {

    private final StringRedisTemplate redisTemplate;

    // 인증 코드 생성 및 저장
    public String generateAndSaveCode(String email) {
        String code = generateRandomCode();
        saveVerificationCode(email, code);
        return code;
    }

    // 인증 코드 검증
    public boolean verifyCode(String email, String code) {
        try {
            String key = getRedisKey(email);
            String storedCode = redisTemplate.opsForValue().get(key);

            if (storedCode == null || !storedCode.equals(code)) {
                throw new EmailException(EmailErrorCode.INVALID_VERIFICATION_CODE);
            }

            redisTemplate.delete(key); // 인증 성공 시 코드 삭제
            return true;
        } catch (Exception e) {
            throw new EmailException(EmailErrorCode.REDIS_OPERATION_FAILED);
        }
    }

    // 랜덤 인증 코드 생성
    private String generateRandomCode() {
        Random random = new Random();
        return String.format("%0" + EmailConstants.VERIFICATION_CODE_LENGTH + "d",
                random.nextInt((int) Math.pow(10, EmailConstants.VERIFICATION_CODE_LENGTH)));
    }

    // 인증 코드 Redis에 저장
    private void saveVerificationCode(String email, String code) {
        try {
            String key = getRedisKey(email);
            redisTemplate.opsForValue().set(key, code,
                    EmailConstants.VERIFICATION_CODE_EXPIRY_MINUTES, TimeUnit.MINUTES);
        } catch (Exception e) {
            throw new EmailException(EmailErrorCode.REDIS_OPERATION_FAILED);
        }
    }

    // Redis 키 생성
    private String getRedisKey(String email) {
        return EmailConstants.REDIS_EMAIL_PREFIX + email;
    }
}
