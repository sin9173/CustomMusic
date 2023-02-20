package com.yg.cm.dto.request.member;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class MemberIdCheckRequestDto {

    @Schema(description = "회원 아이디", required = true, example = "user1")
    private String user_id;
}
