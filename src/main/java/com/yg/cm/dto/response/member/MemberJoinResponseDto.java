package com.yg.cm.dto.response.member;

import com.yg.cm.dto.response.ResponseVO;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class MemberJoinResponseDto extends ResponseVO {

    @Schema(description = "인덱스", example = "1")
    private Long id;

    public MemberJoinResponseDto(Long id) {
        this.id = id;
    }
}
