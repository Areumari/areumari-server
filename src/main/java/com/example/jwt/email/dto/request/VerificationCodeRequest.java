package com.example.jwt.email.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class VerificationCodeRequest {

    @Email(message = "유효한 이메일을 입력해주세요.")
    @NotEmpty(message = "이메일은 필수 항목입니다.")
    private String email;

    @NotEmpty(message = "인증 코드는 필수 항목입니다.")
    private String code;

}