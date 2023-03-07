package com.yg.cm.dto.auth;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class AuthSuccessDto {

    @Schema(description = "JWT Access 토큰", example = "1")
    private String access_token;

    @Schema(description = "JWT Refresh 토큰", example = "1")
    private String refresh_token;

    public AuthSuccessDto(String access_token, String refresh_token) {
        this.access_token = access_token;
        this.refresh_token = refresh_token;
    }
}
