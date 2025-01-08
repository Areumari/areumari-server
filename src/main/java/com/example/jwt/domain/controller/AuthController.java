package com.example.jwt.domain.controller;

import com.example.jwt.domain.dto.LoginRequestDto;
import com.example.jwt.domain.dto.SignupRequestDto;
import com.example.jwt.domain.dto.TokenDto;
import com.example.jwt.domain.dto.TokenRequestDto;
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

    @PostMapping("/signup")
    public ResponseEntity<String> signup(@Valid @RequestBody SignupRequestDto requestDto) {
        authService.signup(requestDto);
        return ResponseEntity.ok("회원가입이 완료되었습니다.");
    }

    @PostMapping("/login")
    public ResponseEntity<TokenDto> login(@Valid @RequestBody LoginRequestDto requestDto) {
        TokenDto jwtResponseDto = authService.login(requestDto);
        return ResponseEntity.ok(jwtResponseDto);
    }

    @PostMapping("/reissue")
    public ResponseEntity<TokenDto> reissue(@RequestBody TokenRequestDto tokenRequestDto) {
        return ResponseEntity.ok(authService.reissue(tokenRequestDto));
    }
}