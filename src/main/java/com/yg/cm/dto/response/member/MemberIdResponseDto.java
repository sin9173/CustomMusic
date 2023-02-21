package com.yg.cm.dto.response.member;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class MemberIdResponseDto {

    @Schema(description = "인덱스", example = "1")
    private Long id;

    public MemberIdResponseDto(Long id) {
        this.id = id;
    }
}
