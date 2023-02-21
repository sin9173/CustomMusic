package com.yg.cm.dto.response.member;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class MemberIdCheckResponseDto {

    @Schema(description = "메세지", example = "사용가능한 아이디입니다.")
    private String message = "사용가능한 아이디 입니다.";

}
