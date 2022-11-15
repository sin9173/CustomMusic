package com.yg.cm.controller;

import io.swagger.v3.oas.annotations.Operation;
import org.springframework.web.bind.annotation.*;

@RestController
public class BoardController {

    @Operation(summary = "댓글(대댓글) 작성", description = "댓글(대댓글)을 작성합니다.")
    @PostMapping("/board/reply")
    public String boardReply(@RequestHeader("userId") String user_id) {
        return null;
    }

    @Operation(summary = "댓글 내용 수정", description = "댓글의 내용을 수정합니다.")
    @PatchMapping("/board/reply/{id}")
    public String boardReplyModify(@PathVariable Long id, @RequestHeader("userId") String user_id) {
        return null;
    }

    @Operation(summary = "댓글 삭제", description = "댓글을 삭제합니다.")
    @DeleteMapping("/board/delete/{id}")
    public String boardReplyDelete(@PathVariable Long id, @RequestHeader("userId") String user_id) {
        return null;
    }

    @Operation(summary = "게시판 파일 등록", description = "게시판의 파일을 등록합니다.")
    @PostMapping("/board/file")
    public String boardFile(@RequestHeader("userId") String user_id) {
        return null;
    }


    @Operation(summary = "게시판 태그 등록", description = "게시판의 태그를 등록합니다.")
    @PostMapping("/board/tag")
    public String boardTag(@RequestHeader("userId") String user_id) {
        return null;
    }

    @Operation(summary = "게시판 태그 조회", description = "게시판의 태그를 조회합니다.")
    @GetMapping("/board/tag")
    public String boardTagList() {
        return null;
    }

    @Operation(summary = "게시판 태그 삭제", description = "게시판의 태그를 삭제합니다.")
    @DeleteMapping("/board/tag")
    public String boardTagDelete(@RequestHeader("userId") String user_id) {
        return null;
    }
}

