package com.yg.cm.controller.web;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.Getter;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @Operation(summary = "테스트", description = "테스트입니다.", tags = {"Test Controller"})
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "OK", content = @Content(schema = @Schema(implementation = Test.class)))
    })
    @GetMapping("/testaa")
    public Test test() {
        Test a = new Test("abc");
        return a;
    }

}

@Schema(description = "테스트 DTO")
@Getter
class Test {

    @Schema(description = "에이", required = true, example = "aaaa")
    private String a;

    public Test(String a) {
        this.a = a;
    }
}
