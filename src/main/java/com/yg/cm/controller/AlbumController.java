package com.yg.cm.controller;

import io.swagger.v3.oas.annotations.Operation;
import org.springframework.web.bind.annotation.*;

@RestController
public class AlbumController {

    @Operation(summary = "앨범게시판작성", description = "앨범 게시판을 작성합니다.")
    @PostMapping("/board/album")
    public String albumWrite(@RequestHeader("userId") String user_id) {
        return null;
    }

    @Operation(summary = "앨범 게시판 수정", description = "앨범 게시판을 수정합니다.")
    @PutMapping("/board/album")
    public String albumModify(@RequestHeader("userId") String user_id) {
        return null;
    }

    @Operation(summary = "앨범 게시판 삭제", description = "앨범 게시판 레코드를 삭제합니다.")
    @DeleteMapping("/boasrd")
    public String albumDelete(@RequestHeader("userId") String user_id) {
        return null;
    }

}
