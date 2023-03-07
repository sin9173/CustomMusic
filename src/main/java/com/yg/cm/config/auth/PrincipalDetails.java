package com.yg.cm.config.auth;

import com.yg.cm.dto.auth.MemberAuthDto;
import com.yg.cm.entity.member.Member;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;

public class PrincipalDetails implements UserDetails {


    private MemberAuthDto memberDto;

    public PrincipalDetails(Member member) {
        this.memberDto = new MemberAuthDto(member.getUserId(), member.getUserPw(), member.getAuthorities());
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Collection<GrantedAuthority> authorities = new ArrayList<>();
        memberDto.getAuthorities().stream().forEach(authority -> {
            authorities.add(() -> authority.toString());
        });
        return authorities;
    }

    @Override
    public String getPassword() {
        return memberDto.getPassword();
    }

    @Override
    public String getUsername() {
        return memberDto.getUsername();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }


}
