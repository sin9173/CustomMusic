package com.yg.cm.controller;


import io.swagger.v3.oas.annotations.Operation;
import org.springframework.web.bind.annotation.*;

@RestController
public class MemberController {

    @Operation(summary = "회원가입", description = "회원가입을 합니다.")
    @PostMapping("/member")
    public String member() {
        return null;
    }

    @Operation(summary = "회원 아이디 체크", description = "아이디 사용 가능여부를 확인합니다.")
    @GetMapping("/member/idcheck/{id}")
    public String idCheck(@PathVariable Long id) {
        return null;
    }

    @Operation(summary = "비밀번호 수정", description = "비밀번호 수정")
    public String password_modify(@RequestHeader("userId") String user_id) {
        return null;
    }

    @Operation(summary = "로그인", description = "로그인을 합니다.")
    @PostMapping("/auth")
    public String auth() {
        return null;
    }

    @Operation(summary = "회원수정", description = "회원정보를 수정합니다.")
    @PutMapping("/member")
    public String memberModify(@RequestHeader("userId") String user_id) {
        return null;
    }

    @Operation(summary = "회원탈퇴", description = "회원탈퇴를 합니다.")
    @DeleteMapping("/member")
    public String memberDelete(@RequestHeader("userId") String user_id) {
        return null;
    }


}
