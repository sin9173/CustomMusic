package com.yg.cm.service;

import com.yg.cm.dto.request.member.MemberJoinRequestDto;
import com.yg.cm.dto.request.member.MemberPasswordModifyRequestDto;
import com.yg.cm.dto.response.ResponseDto;
import com.yg.cm.dto.response.ResultCode;
import com.yg.cm.dto.response.ResultMessage;
import com.yg.cm.dto.response.member.MemberIdCheckResponseDto;
import com.yg.cm.dto.response.member.MemberIdResponseDto;
import com.yg.cm.entity.member.Member;
import com.yg.cm.service.query.MemberQueryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MemberService {

    private final MemberQueryService memberQueryService;

    //회원가입
    @Transactional
    public ResponseEntity<ResponseDto<MemberIdResponseDto>> memberJoin(MemberJoinRequestDto dto) {
        return ResponseDto.getResponse(new MemberIdResponseDto(memberQueryService.memberJoin(dto)));
    }

    //아이디 중복검사
    public ResponseEntity<ResponseDto<MemberIdCheckResponseDto>> idCheck(String user_id) {
        MemberIdCheckResponseDto body = new MemberIdCheckResponseDto();
        if(memberQueryService.isMemberEmpty(user_id)) {
            return ResponseDto.getResponse(body);
        } else {
            return ResponseDto.getResponse(409, ResultCode.CONFLICT, ResultMessage.DUPLICATE_ID); // CONFLICT STATUS
        }
    }

    //비밀번호 변경
    @Transactional
    public ResponseEntity<ResponseDto<MemberIdResponseDto>> passwordModify(MemberPasswordModifyRequestDto dto, String user_id) {
        if(dto.isBadPassword(dto)) {
            return ResponseDto.getResponse(422, ResultCode.BAD_INFO, ResultMessage.BAD_INFO_PASSWORD);
        }
        Member member = memberQueryService.getAuthMember(user_id, dto.getPassword());
        if(member==null) return ResponseDto.getResponse(401, ResultCode.UNAUTHORIZED, ResultMessage.UNAUTHORIZED);
        Long id = memberQueryService.passwordModify(member, dto.getPassword_new());
        return ResponseDto.getResponse(new MemberIdResponseDto(id));
    }





}
