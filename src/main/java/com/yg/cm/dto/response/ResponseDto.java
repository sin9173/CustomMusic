package com.yg.cm.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.http.ResponseEntity;

@Data
@ToString
public class ResponseDto<T> {

    @Schema(description = "응답코드", example = "20000")
    private Integer code = ResultCode.SUCCESS;

    @Schema(description = "응답메세지", example = "성공")
    private String message = ResultMessage.SUCCESS;

    private T data;


    private ResponseDto(T data) {
        this.data = data;
    }

    public static <T>ResponseEntity getResponse(T data) {
        return ResponseEntity.ok()
                .body(new ResponseDto<T>(data));
    }

    public static <T> ResponseEntity getResponse(Integer status, Integer code, String message) {
        return ResponseEntity.status(status)
                .body(new ErrorResponseDto(code, message));
    }
}
