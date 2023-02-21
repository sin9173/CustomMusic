package com.yg.cm.dto.request.member;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.springframework.util.StringUtils;

@Data
public class MemberPasswordModifyRequestDto {

    @Schema(description = "비밀번호", required = true, example = "비밀번호")
    private String password;

    @Schema(description = "신규 비밀번호", required = true, example = "신규비밀번호")
    private String password_new;

    @Schema(description = "비밀번호 확인", required = true, example = "확인")
    private String password_check;

    public boolean isBadPassword(MemberPasswordModifyRequestDto dto) {
        if(!StringUtils.hasText(dto.getPassword())) {
            return true;
        }
        if(!StringUtils.hasText(dto.getPassword_new())) {
            return true;
        } else if(!dto.getPassword_new().equals(dto.getPassword_check())) {
            return true;
        }
        return false;
    }
}
