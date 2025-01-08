package com.example.jwt.domain.controller;

import com.example.jwt.domain.dto.*;
import com.example.jwt.domain.service.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/signUp")
    public ResponseEntity<String> signUp(@Valid @RequestBody SignupRequestDto requestDto) {
        authService.signup(requestDto);
        return ResponseEntity.ok("회원가입이 완료되었습니다.");
    }

    @PostMapping("/signIn")
    public ResponseEntity<LoginResponseDto> signIn(@Valid @RequestBody LoginRequestDto requestDto) {
        TokenDto jwtResponseDto = authService.login(requestDto);
        String welcomeMessage = "환영합니다, " + requestDto.getName() + "님!";
        LoginResponseDto responseDto = new LoginResponseDto(jwtResponseDto, welcomeMessage);
        return ResponseEntity.ok(responseDto);
    }

    @PostMapping("/reissue")
    public ResponseEntity<TokenDto> reissue(@RequestBody TokenRequestDto tokenRequestDto) {
        return ResponseEntity.ok(authService.reissue(tokenRequestDto));
    }
}