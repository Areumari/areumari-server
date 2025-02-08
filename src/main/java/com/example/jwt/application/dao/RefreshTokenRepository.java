package com.example.jwt.application.dao;

import com.example.jwt.domain.domain.RefreshToken;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RefreshTokenRepository extends JpaRepository<RefreshToken, Long> {
    Optional<RefreshToken> findBySnumber(String snumber);
    Optional<RefreshToken> findByTokenValue(String tokenValue);
}