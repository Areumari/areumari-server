package com.example.jwt.domain.service;

import com.example.jwt.application.dao.MemberRepository;
import com.example.jwt.application.dao.RefreshTokenRepository;
import com.example.jwt.domain.domain.Member;
import com.example.jwt.domain.domain.RefreshToken;
import com.example.jwt.domain.dto.LoginRequestDto;
import com.example.jwt.domain.dto.SignupRequestDto;
import com.example.jwt.domain.dto.TokenDto;
import com.example.jwt.domain.dto.TokenRequestDto;
import com.example.jwt.domain.jwt.exception.TokenValidationException;
import com.example.jwt.domain.jwt.service.TokenService;
import com.example.jwt.email.service.EmailService;
import com.example.jwt.email.service.VerificationStatusService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final AuthenticationManager authenticationManager;
    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;
    private final TokenService tokenService;
    private final RefreshTokenRepository refreshTokenRepository;
    private final EmailService emailService;
    private final VerificationStatusService verificationStatusService;

    @Transactional
    public void signup(SignupRequestDto requestDto) {
        if (memberRepository.existsBySnumber(requestDto.getSnumber())) {
            throw new IllegalArgumentException("이미 가입된 학번입니다.");
        }

        Member member = Member.builder()
                .name(requestDto.getName())
                .password(passwordEncoder.encode(requestDto.getPassword()))
                .snumber(requestDto.getSnumber())
                .authority(requestDto.getAuthority())
                .build();

        memberRepository.save(member);
    }

    @Transactional
    public void sendVerificationEmail(String email) {
        emailService.sendVerificationEmail(email);
    }

    @Transactional
    public TokenDto login(LoginRequestDto requestDto) {
        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(requestDto.getSnumber(), requestDto.getPassword());

        Authentication authentication = authenticationManager.authenticate(authenticationToken);
        TokenDto tokenDto = tokenService.createTokenDto(authentication);

        RefreshToken refreshToken = refreshTokenRepository.findBySnumber(authentication.getName())
                .orElseGet(() -> RefreshToken.builder()
                        .snumber(authentication.getName())
                        .tokenValue(tokenDto.getRefreshToken())
                        .build());

        refreshToken.updateValue(tokenDto.getRefreshToken());
        refreshTokenRepository.save(refreshToken);

        return tokenDto;
    }

    @Transactional
    public TokenDto reissue(TokenRequestDto tokenRequestDto) {
        try {
            if (!tokenService.validateToken(tokenRequestDto.getRefreshToken())) {
                throw new TokenValidationException("유효하지 않은 Refresh Token입니다.");
            }
        } catch (TokenValidationException e) {
            throw new TokenValidationException("Refresh Token이 만료되었거나 유효하지 않습니다. 토큰을 확인하세요.");
        }

        Authentication authentication = tokenService.getAuthentication(tokenRequestDto.getAccessToken());

        RefreshToken refreshToken = refreshTokenRepository.findBySnumber(authentication.getName())
                .orElseThrow(() -> new IllegalArgumentException("로그아웃된 사용자입니다."));

        if (!refreshToken.getTokenValue().equals(tokenRequestDto.getRefreshToken())) {
            throw new TokenValidationException("토큰의 유저 정보가 일치하지 않습니다.");
        }

        TokenDto tokenDto = tokenService.createTokenDto(authentication);
        refreshToken.updateValue(tokenDto.getRefreshToken());
        refreshTokenRepository.save(refreshToken);

        return tokenDto;
    }
}