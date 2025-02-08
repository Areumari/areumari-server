package com.example.jwt.domain.domain;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RefreshToken {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String snumber;    // snumber 필드 추가

    @Column(nullable = false)
    private String tokenValue;

    public void updateValue(String tokenValue) {
        this.tokenValue = tokenValue;
    }
}