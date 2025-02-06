package com.example.jwt.domain.controller;

import com.example.jwt.domain.dto.*;
import com.example.jwt.domain.service.AuthService;
import com.example.jwt.email.dto.request.EmailVerificationRequest;
import com.example.jwt.email.dto.request.VerificationCodeRequest;
import com.example.jwt.email.dto.response.ApiResponse;
import com.example.jwt.email.service.EmailService;
import com.example.jwt.email.service.VerificationCodeService;
import com.example.jwt.email.service.VerificationStatusService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
@Tag(name = "Authentication", description = "회원 인증 API") // Swagger Tag 설정
public class AuthController {

    private final AuthService authService;
    private final EmailService emailService;
    private final VerificationCodeService verificationCodeService;
    private final VerificationStatusService verificationStatusService;

    @Operation(summary = "인증 이메일 전송", description = "회원가입을 위한 인증 이메일을 전송합니다.")
    @PostMapping("/sendVerificationEmail")
    public ResponseEntity<ApiResponse<String>> sendVerificationEmail(@Valid @RequestBody EmailVerificationRequest request) {
        // 인증 코드 생성 및 이메일 전송
        String code = verificationCodeService.generateAndSaveCode(request.getEmail());
        emailService.sendVerificationEmail(request.getEmail());
        return ResponseEntity.ok(ApiResponse.success("인증 이메일이 전송되었습니다."));
    }

    @Operation(summary = "인증 코드 확인", description = "회원가입을 위한 인증 코드를 확인합니다.")
    @PostMapping("/verifyCode")
    public ResponseEntity<ApiResponse<String>> verifyCode(@Valid @RequestBody VerificationCodeRequest request) {
        boolean isVerified = verificationCodeService.verifyCode(request.getEmail(), request.getCode());
        if (isVerified) {
            verificationStatusService.markEmailAsVerified(request.getEmail());
            return ResponseEntity.ok(ApiResponse.success("이메일 인증이 완료되었습니다."));
        }
        return ResponseEntity.badRequest().body(ApiResponse.error("인증 코드가 유효하지 않습니다."));
    }

    // 회원가입
    @Operation(summary = "회원가입", description = "회원가입과 이메일 인증을 처리하는 API입니다.")
    @PostMapping("/signUp")
    public ResponseEntity<ApiResponse<String>> signUp(@Valid @RequestBody SignupRequestDto requestDto) {
        authService.signup(requestDto);
        return ResponseEntity.ok(ApiResponse.success("회원가입이 완료되었습니다."));
    }

    @Operation(summary = "로그인", description = "로그인을 위한 API입니다. JWT 토큰과 환영 메시지를 반환합니다.")
    @PostMapping("/signIn")
    public ResponseEntity<LoginResponseDto> signIn(@Valid @RequestBody LoginRequestDto requestDto) {
        TokenDto jwtResponseDto = authService.login(requestDto);
        String welcomeMessage = "환영합니다, " + requestDto.getName() + "님!";
        LoginResponseDto responseDto = new LoginResponseDto(jwtResponseDto, welcomeMessage);
        return ResponseEntity.ok(responseDto);
    }

    @Operation(summary = "토큰 재발급", description = "Access 토큰을 재발급 받기 위한 API입니다.")
    @PostMapping("/reissue")
    public ResponseEntity<TokenDto> reissue(@Valid @RequestBody TokenRequestDto tokenRequestDto) {
        TokenDto newTokenDto = authService.reissue(tokenRequestDto);
        return ResponseEntity.ok(newTokenDto);
    }
}