package com.yg.cm.service.query;

import com.yg.cm.dto.request.member.MemberJoinRequestDto;
import com.yg.cm.dto.request.member.MemberPasswordModifyRequestDto;
import com.yg.cm.entity.member.Member;
import com.yg.cm.repository.member.MemberRepository;
import com.yg.cm.repository.member.ProfileRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class MemberQueryService {

    private final MemberRepository memberRepository;

    private final ProfileRepository profileRepository;

    public Long memberJoin(MemberJoinRequestDto memberJoinRequestDto) { //회원 저장
        return memberRepository.save(new Member(memberJoinRequestDto)).getId();
    }

    public boolean isMemberEmpty(String user_id) { //아이디 존재여부
        return memberRepository.findByUserId(user_id).isEmpty();
    }


    public Long passwordModify(Member member, String password) { //비밀번호 변경
        member.updatePassword(password);
        return member.getId();
    }

    public Member getAuthMember(String user_id, String password) { //회원 인증 후 가져오기
        Member member = memberRepository.findByUserId(user_id).get();
        if(!member.getUserPw().equals(password)) return null;
        return member;
    }

}
