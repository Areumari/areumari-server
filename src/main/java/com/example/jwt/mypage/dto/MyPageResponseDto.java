package com.example.jwt.mypage.dto;

import com.example.jwt.domain.domain.Member;
import lombok.Getter;

@Getter
public class MyPageResponseDto {
    private String name;
    private String snumber;
    private String authority;

    public MyPageResponseDto(Member member) {
        this.name = member.getName();
        this.snumber = member.getSnumber();
        this.authority = member.getAuthority().name();
    }
}
