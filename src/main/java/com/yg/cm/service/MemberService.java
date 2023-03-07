package com.yg.cm.service;

import com.yg.cm.config.auth.PrincipalDetails;
import com.yg.cm.dto.auth.AuthSuccessDto;
import com.yg.cm.dto.request.member.MemberJoinRequestDto;
import com.yg.cm.dto.request.member.MemberPasswordModifyRequestDto;
import com.yg.cm.dto.response.ResponseDto;
import com.yg.cm.dto.response.ResultCode;
import com.yg.cm.dto.response.ResultMessage;
import com.yg.cm.dto.response.member.MemberIdCheckResponseDto;
import com.yg.cm.dto.response.member.MemberIdResponseDto;
import com.yg.cm.entity.member.Member;
import com.yg.cm.jwt.TokenProvider;
import com.yg.cm.service.query.MemberQueryService;
import com.yg.cm.util.HttpUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletResponse;
import java.util.concurrent.TimeUnit;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MemberService {

    private final MemberQueryService memberQueryService;

    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    private final RedisTemplate<String, Object> redisTemplate;

    private final TokenProvider tokenProvider;

    //회원가입
    @Transactional
    public ResponseEntity<ResponseDto<MemberIdResponseDto>> memberJoin(MemberJoinRequestDto dto) {
        dto.setUser_pw(bCryptPasswordEncoder.encode(dto.getUser_pw()));
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
        Member member = memberQueryService.getMember(user_id);
        if(!bCryptPasswordEncoder.matches(dto.getPassword(), member.getUserPw())) return ResponseDto.getResponse(401, ResultCode.UNAUTHORIZED, ResultMessage.UNAUTHORIZED);
        Long id = memberQueryService.passwordModify(member, bCryptPasswordEncoder.encode(dto.getPassword_new()));
        return ResponseDto.getResponse(new MemberIdResponseDto(id));
    }

    public ResponseEntity<ResponseDto<AuthSuccessDto>> reissue(String user_id, HttpServletResponse response) {
        String refresh_token_cli = HttpUtil.getCookieParam("refresh_token");
        String refresh_token_redis = (String) redisTemplate.opsForValue().get("RefreshToken:" + user_id);
        if(!StringUtils.hasText(refresh_token_redis) || !refresh_token_redis.equals(refresh_token_cli)) {
            return ResponseDto.getResponse(401, ResultCode.TOKEN_INVALID, ResultMessage.TOKEN_INVALID);
        }

        Member member = memberQueryService.getAuthMember(user_id);
        PrincipalDetails principalDetails = new PrincipalDetails(member);
        Authentication authentication = new UsernamePasswordAuthenticationToken(principalDetails, "1", principalDetails.getAuthorities());

        AuthSuccessDto tokenDto = tokenProvider.createToken(authentication);

        redisTemplate.opsForValue()
                .set("RefreshToken:" + authentication.getName(), tokenDto.getRefresh_token(),
                        tokenProvider.getTokenRefreshMillis(), TimeUnit.MILLISECONDS);

        response.addHeader(TokenProvider.AUTHORIZATION_HEADER, "Bearer " + tokenDto.getAccess_token());
        HttpUtil.refreshTokenCookie(tokenDto.getRefresh_token(), response, (int) tokenProvider.getTokenRefreshMillis());

        return ResponseDto.getResponse(tokenDto);
    }




}
