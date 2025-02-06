package com.example.jwt.email.service;

import org.springframework.stereotype.Service;

@Service
public class EmailTemplateService {
    public String generateVerificationEmailTemplate(String code) {
        return "<div style='font-family: Arial, sans-serif; text-align: center; padding: 20px; color: #333;'>"
                + "<h2 style='color: #3379D9;'>아름아리 이메일 인증</h2>"
                + "<p style='font-size: 16px;'>안녕하세요,</p>"
                + "<p style='font-size: 16px;'>아래의 인증 코드를 입력하여 이메일 인증을 완료해 주세요:</p>"
                + "<div style='font-size: 24px; font-weight: bold; margin: 20px 0; color: #3379D9;'>" + code + "</div>"
                + "<p style='font-size: 14px; color: #666;'>인증 코드는 3분간 유효합니다.</p>"
                + "<p style='font-size: 14px; color: #666;'>아름아리 서비스를 이용해 주셔서 감사합니다.</p>"
                + "</div>";
    }
}