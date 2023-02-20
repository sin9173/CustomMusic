package com.yg.cm.entity.file;

import com.yg.cm.entity.board.Board;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@DiscriminatorValue("BOARD")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class BoardFile extends Files { //게시판 파일

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "board_id")
    private Board board;

    public BoardFile(String fileName, String filePath) {
        updateFileInfo(fileName, filePath);
    }
}
