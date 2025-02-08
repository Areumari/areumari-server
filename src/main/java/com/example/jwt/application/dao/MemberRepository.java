package com.example.jwt.application.dao;

import com.example.jwt.domain.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {
    Optional<Member> findBySnumber(String snumber);
    boolean existsBySnumber(String snumber);
}