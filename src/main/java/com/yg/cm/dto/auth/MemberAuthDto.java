package com.yg.cm.dto.auth;

import com.yg.cm.entity.member.Authority;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Getter
@Setter
@NoArgsConstructor
public class MemberAuthDto {

    private String username;

    private String password;

    private List<String> authorities;

    public MemberAuthDto(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public MemberAuthDto(String username, String password, List<Authority> authorities) {
        this.username = username;
        this.password = password;
        this.authorities = authorities.stream()
                .map(authority -> authority.getRole().toString())
                .collect(Collectors.toList());
    }


}
