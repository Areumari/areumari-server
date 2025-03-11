package com.example.jwt.mypage.service;

import com.example.jwt.application.dao.MemberRepository;
import com.example.jwt.domain.domain.Member;
import com.example.jwt.mypage.dto.MyPageResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;

    public MyPageResponseDto getMyPage() {
        // 현재 로그인한 사용자 정보 가져오기
        String currentUsername = getCurrentUsername();
        Member member = memberRepository.findBySnumber(currentUsername)
                .orElseThrow(() -> new IllegalArgumentException("사용자를 찾을 수 없습니다."));

        return new MyPageResponseDto(member);
    }

    private String getCurrentUsername() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof UserDetails) {
            return ((UserDetails) principal).getUsername(); // username = s_number
        } else {
            return principal.toString();
        }
    }
}
