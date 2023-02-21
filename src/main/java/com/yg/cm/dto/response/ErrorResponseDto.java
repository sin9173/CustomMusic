package com.yg.cm.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.HashMap;

@Data
public class ErrorResponseDto {

    @Schema(description = "응답코드", example = "50000")
    private Integer code;

    @Schema(description = "응답메세지", example = "에러")
    private String message;

    private HashMap<String, String> data = new HashMap<>();

    public ErrorResponseDto(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}
