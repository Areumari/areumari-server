package com.example.jwt.domain.controller;

import com.example.jwt.domain.dto.*;
import com.example.jwt.domain.service.AuthService;
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

    @Operation(summary = "회원가입", description = "회원가입을 위한 API입니다.")
    @PostMapping("/signUp")
    public ResponseEntity<String> signUp(@Valid @RequestBody SignupRequestDto requestDto) {
        authService.signup(requestDto);
        return ResponseEntity.ok("회원가입이 완료되었습니다.");
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
    public ResponseEntity<TokenDto> reissue(@RequestBody TokenRequestDto tokenRequestDto) {
        return ResponseEntity.ok(authService.reissue(tokenRequestDto));
    }
}
