package com.example.jwt.email.constants;

public class EmailConstants {
    public static final String EMAIL_SUBJECT = "아름아리 이메일 인증";
    public static final int VERIFICATION_CODE_LENGTH = 6;
    public static final int VERIFICATION_CODE_EXPIRY_MINUTES = 3;
    public static final String REDIS_EMAIL_PREFIX = "email:verification:";
    public static final int VERIFIED_STATUS_EXPIRY_HOURS = 24;
    public static final String REDIS_VERIFIED_PREFIX = "verified_email:";

}