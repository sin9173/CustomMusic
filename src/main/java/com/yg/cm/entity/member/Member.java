package com.yg.cm.entity.member;

import com.yg.cm.dto.request.member.MemberJoinRequestDto;
import com.yg.cm.entity.TimeAndDelete;
import com.yg.cm.entity.member.enums.Role;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.util.StringUtils;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Getter
@Table(name = "member")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Member extends TimeAndDelete { //회원 엔티티

    @Id
    @GeneratedValue
    @Column(name = "member_id")
    private Long id; //인덱스

    private String userId; //아이디

    private String userPw; //비밀번호

    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL)
    private List<Authority> authorities = new ArrayList<>();

    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL)
    private List<Profile> profiles = new ArrayList<>();


    public Member(String userId, String user_pw) {
        this.userId = userId;
        this.userPw = user_pw;
        this.authorities.add(new Authority(Role.ROLE_USER, this));
    }

    public Member(MemberJoinRequestDto dto) {
        this.userId = dto.getUser_id();
        this.userPw = dto.getUser_pw();
        this.authorities.add(new Authority(Role.ROLE_USER, this));
    }

    public void addProfile(Profile profile) {
        if(profile!=null) {
            profiles.add(profile);
        }
    }

    public void updatePassword(String password) {
        if(StringUtils.hasText(password)) this.userPw = password;
    }
}
