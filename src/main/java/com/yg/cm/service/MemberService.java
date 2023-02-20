package com.yg.cm.service;

import com.yg.cm.dto.request.member.MemberJoinRequestDto;
import com.yg.cm.dto.response.member.MemberJoinResponseDto;
import com.yg.cm.service.query.MemberQueryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberQueryService memberQueryService;

    public MemberJoinResponseDto memberJoin(MemberJoinRequestDto dto) { //회원가입
        return new MemberJoinResponseDto(memberQueryService.memberJoin(dto));
    }

}
