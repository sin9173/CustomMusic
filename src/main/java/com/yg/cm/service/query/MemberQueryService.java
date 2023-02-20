package com.yg.cm.service.query;

import com.yg.cm.dto.request.member.MemberJoinRequestDto;
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

    @Transactional
    public Long memberJoin(MemberJoinRequestDto memberJoinRequestDto) {
        return memberRepository.save(new Member(memberJoinRequestDto)).getId();
    }
}
