package com.example.jwt.domain.service;

import com.example.jwt.application.dao.MemberRepository;
import com.example.jwt.domain.domain.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {
    private final MemberRepository memberRepository;

    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String snumber) throws UsernameNotFoundException {
        return memberRepository.findBySnumber(snumber)
                .map(this::createUserDetails)
                .orElseThrow(() -> new UsernameNotFoundException(snumber + " -> 해당하는 유저를 찾을 수 없습니다."));
    }

    private UserDetails createUserDetails(Member member) {
        GrantedAuthority grantedAuthority = new SimpleGrantedAuthority(member.getAuthority().toString());

        return new User(
                member.getSnumber(),
                member.getPassword(),
                Collections.singleton(grantedAuthority)
        );
    }
}