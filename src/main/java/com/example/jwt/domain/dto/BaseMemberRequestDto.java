package com.example.jwt.domain.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public abstract class BaseMemberRequestDto {

    @Size(min = 1, max = 10,message = "이름은 10자리 이상입력 불가합니다")
    @NotBlank(message = "이름은 필수 입력값입니다.")
    private String name;

    @Size(max = 5, message = "학번은 4자리 이상입력 불가합니다")
    private String s_number;

    @Email(message = "유효한 이메일 형식을 입력하세요.")
    @NotBlank(message = "이메일은 필수 입력값입니다.")
    private String email;

    @NotBlank(message = "비밀번호는 필수 입력값입니다.")
    private String password;
}
