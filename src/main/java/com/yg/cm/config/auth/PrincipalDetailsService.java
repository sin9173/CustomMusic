package com.yg.cm.config.auth;

import com.yg.cm.entity.member.Member;
import com.yg.cm.repository.member.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class PrincipalDetailsService implements UserDetailsService {

    private final MemberRepository memberRepository;

    // UserDetails -> Authentication -> SecuritySession (SecuritySession 에 UserDetails 가 들어가면 로그인이 완료)
    // 로그인시 자동으로 loadUserByUsername 이 실행된 후 AuthenticationPrincipal 이 생성됨
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        System.out.println("loadUserByUsername 실행됨");
        Optional<Member> member = memberRepository.findAuthByUserId(username);
        if(member.isEmpty()) return null;
        return new PrincipalDetails(member.get());
    }
}
