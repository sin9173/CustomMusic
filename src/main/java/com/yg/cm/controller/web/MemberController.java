package com.yg.cm.controller.web;


import com.yg.cm.dto.auth.AuthSuccessDto;
import com.yg.cm.dto.request.member.MemberJoinRequestDto;
import com.yg.cm.dto.request.member.MemberPasswordModifyRequestDto;
import com.yg.cm.dto.response.ErrorResponseDto;
import com.yg.cm.dto.response.ResponseDto;
import com.yg.cm.dto.response.member.MemberIdCheckResponseDto;
import com.yg.cm.dto.response.member.MemberIdResponseDto;
import com.yg.cm.service.MemberService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;

@RestController
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    @Operation(summary = "회원가입", description = "회원가입을 합니다.")
    @PostMapping("/member")
    public ResponseEntity<ResponseDto<MemberIdResponseDto>> member(@RequestBody MemberJoinRequestDto data) {
        return memberService.memberJoin(data);
    }

    @Operation(summary = "회원 아이디 체크", description = "아이디 사용 가능여부를 확인합니다.", responses = {
            @ApiResponse(responseCode = "200", description = "OK", useReturnTypeSchema = true),
            @ApiResponse(responseCode = "409", description = "중복된 아이디", content = @Content(schema = @Schema(implementation = ErrorResponseDto.class)))
    })
    @GetMapping("/member/idcheck/{user_id}")
    public ResponseEntity<ResponseDto<MemberIdCheckResponseDto>> idCheck(@PathVariable String user_id) {
        return memberService.idCheck(user_id);
    }

    @PreAuthorize("hasRole('ROLE_USER')")
    @Operation(summary = "비밀번호 수정", description = "비밀번호 수정", responses = {
            @ApiResponse(responseCode = "200", description = "OK", useReturnTypeSchema = true),
            @ApiResponse(responseCode = "422", description = "잘못된 비밀번호", content = @Content(schema = @Schema(implementation = ErrorResponseDto.class)))
    })
    @PatchMapping("/member/password")
    public ResponseEntity<ResponseDto<MemberIdResponseDto>> password_modify(
            @RequestHeader("userId") String user_id,
            @RequestBody MemberPasswordModifyRequestDto data) {
        return memberService.passwordModify(data, user_id);
    }

    @Operation(summary = "로그인", description = "로그인을 합니다.")
    @PostMapping("/login")
    public String auth() {
        return "로그인";
    }

    @Operation(summary = "토큰재발급", description = "토큰을 재발급 받습니다.")
    @PostMapping("/reissue")
    public ResponseEntity<ResponseDto<AuthSuccessDto>> reissue(
            @RequestHeader("userId") String user_id,
            HttpServletResponse response) {
        return memberService.reissue(user_id, response);
    }

    @PreAuthorize("hasRole('ROLE_USER')")
    @Operation(summary = "회원수정", description = "회원정보를 수정합니다.")
    @PutMapping("/member")
    public String memberModify(@RequestHeader("userId") String user_id) {
        return null;
    }

    @PreAuthorize("hasRole('ROLE_USER')")
    @Operation(summary = "회원탈퇴", description = "회원탈퇴를 합니다.")
    @DeleteMapping("/member")
    public String memberDelete(@RequestHeader("userId") String user_id) {
        return null;
    }

    @Operation(summary = "프로필 이미지 등록", description = "프로필 이미지를 등록합니다.")
    @PostMapping("/profile/image")
    public String profileImage(@RequestHeader("userId") String user_id) {
        return null;
    }

    @Operation(summary = "프로필 이미지 수정", description = "프로필 이미지 정보를 수정합니다.")
    @PatchMapping("/profile/image")
    public String profileImageModify(@RequestHeader("userId") String user_id) {
        return null;
    }

    @Operation(summary = "프로필 이미지 삭제", description = "프로필 이미지를 삭제합니다.")
    @DeleteMapping("/profile/image")
    public String profileImageDelete(@RequestHeader("userId") String user_id) {
        return null;
    }

}
