package com.yg.cm.controller.web;

import io.swagger.v3.oas.annotations.Operation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CommunityController {

    @Operation(summary = "커뮤니티 게시판 작성", description = "커뮤니티 게시판을 작성합니다.")
    @PostMapping("/board/community")
    public String boardCommunity(@RequestHeader("userId") String user_id) {
        return null;
    }

    @Operation(summary = "커뮤니티 게시판 리스트 조회", description = "커뮤니티 게시판 리스트를 조회합니다.")
    @GetMapping("/board/community")
    public String boardCommunityList(@RequestHeader("userId") String user_id) {
        return null;
    }


}
