package com.example.jwt.domain.dto;

import com.example.jwt.domain.domain.Authority;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
public class SignupRequestDto extends BaseMemberRequestDto {

    private Authority authority;
}